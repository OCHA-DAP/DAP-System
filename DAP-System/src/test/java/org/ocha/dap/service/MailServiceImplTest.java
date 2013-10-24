package org.ocha.dap.service;

import javax.mail.internet.AddressException;

import org.junit.Test;

public class MailServiceImplTest {

	@Test
	public void testSendMail() throws AddressException {
		final MailService mailService = new MailServiceImpl("exchsrv05.hitec.lan", 25, "no-reply@emergency.lu");
		mailService.sendMail("samuel.eustachi@hitec.lu", "testSendMail", "testSendMailBody");
	}
}
