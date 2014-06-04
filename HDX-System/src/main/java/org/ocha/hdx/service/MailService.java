package org.ocha.hdx.service;

import org.ocha.hdx.importer.ImportReport;
import org.ocha.hdx.model.validation.ValidationReport;

public interface MailService {

	public void sendMail(final String recipient, final String mailSubject, final String mailBody);

	public void sendMailForResourceEvaluationFailure(final String id, final String revision_id, final ValidationReport report);

	public void sendMailForResourceImportFailure(final String id, final String revision_id);

	public void sendMailForResourceImportFailure(final String id, final String revision_id, final ImportReport importReport);

	public String getMaintainerForFile(final String id, final String revision_id);

}
