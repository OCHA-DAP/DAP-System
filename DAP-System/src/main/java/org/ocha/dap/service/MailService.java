package org.ocha.dap.service;

import org.ocha.dap.persistence.entity.ckan.CKANDataset;


public interface MailService {
	
	public void sendMail(final String recipient, final String mailSubject, final String mailBody);
	
	public void sendMailForResourceEvaluationFailure(final String id, final String revision_id, final CKANDataset.Type evaluator);
	public void sendMailForResourceImportFailure(final String id, final String revision_id, final CKANDataset.Type evaluator);
	
	public String getMaintainerForFile(final String id, final String revision_id);

}
