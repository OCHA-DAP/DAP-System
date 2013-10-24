package org.ocha.dap.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.ocha.dap.persistence.dao.CKANDatasetDAO;
import org.ocha.dap.persistence.dao.CKANResourceDAO;
import org.ocha.dap.persistence.entity.CKANDataset.Type;
import org.ocha.dap.persistence.entity.CKANResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.mail.smtp.SMTPTransport;

public class MailServiceImpl implements MailService {
	
	@Autowired
	private CKANResourceDAO resourceDAO;
	
	@Autowired
	private CKANDatasetDAO datasetDAO;
	
	private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

	private final String smtpHost;
	private final Integer smtpPort;
	private final InternetAddress from;

	public MailServiceImpl(final String smtpHost, final Integer smtpPort, final String from) throws AddressException {
		super();
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
		this.from = new InternetAddress(from);
	}

	// Timeouts to use for the connection to the mail server
	private static final int DEFAULT_CONNECT_TIMEOUT_SECONDS = 45;
	private static final int DEFAULT_READ_TIMEOUT_SECONDS = 45;

	@Override
	public void sendMail(final String recipient, final String mailSubject, final String mailBody) {

		SMTPTransport t = null;
		final Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtpHost);
		props.setProperty("mail.smtp.port", smtpPort.toString());
		props.setProperty("mail.smtp.connectiontimeout", String.valueOf(DEFAULT_CONNECT_TIMEOUT_SECONDS * 1000));
		props.setProperty("mail.smtp.timeout", String.valueOf(DEFAULT_READ_TIMEOUT_SECONDS * 1000));

		// Should cause the client to close the connection immediately
		// after sending QUIT, without waiting for a response anymore then
		props.setProperty("mail.smtp.quitwait", "false");

		final Session s = Session.getInstance(props, null);

		final Message msg = new MimeMessage(s);
		try {
			msg.setFrom(from);
			msg.setRecipient(RecipientType.TO, new InternetAddress(recipient));
			msg.setSentDate(new Date());
			msg.setSubject(mailSubject);
			msg.setText(mailBody);

			t = (SMTPTransport) s.getTransport("smtp");
			t.connect();
			SMTPTransport.send(msg);
		} catch (final MessagingException e) {
			log.error(e.toString(), e);
		} finally {
			if (t != null) {
				try {
					t.close();
				} catch (final Exception e) {
					log.warn(String.format("Failed to close SMTPTransport: %s", e.getMessage()), e);
				}
			}
		}
	}

	@Override
	public void sendMailForResourceEvaluationFailure(final String id, final String revision_id, final Type evaluator) {
		final CKANResource res = resourceDAO.getCKANResource(id, revision_id);
		
		final String mailSubject = String.format("CKAN Resource %s evaluation failed, for type : %s", res.getName(), res.getEvaluator());
		final String mailBody = String.format("CKAN Resource %s evaluation failed, for type : %s", res.getName(), res.getEvaluator());
		sendMail(getMaintainerForFile(id, revision_id), mailSubject, mailBody);
		
	}

	@Override
	public String getMaintainerForFile(final String id, final String revision_id) {
		final CKANResource ckanResource = resourceDAO.getCKANResource(id, revision_id);
		return datasetDAO.getMaintainerMailForName(ckanResource.getParentDataset_name());
	}

}
