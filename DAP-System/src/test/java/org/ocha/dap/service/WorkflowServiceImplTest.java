package org.ocha.dap.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.entity.CKANResource;
import org.ocha.dap.persistence.entity.CKANResource.WorkflowState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml",
		"classpath:/ctx-persistence-test.xml" })
public class WorkflowServiceImplTest {
	
	@Autowired
	private WorkflowService workflowService;

	@Test
	public void testNextStateIsPossible() {
		final CKANResource resource = new CKANResource("id", "revision_id", true, "theParent");
		Assert.assertTrue(workflowService.nextStateIsPossible(resource, WorkflowState.DOWNLOADED));
		Assert.assertTrue(workflowService.nextStateIsPossible(resource, WorkflowState.OUTDATED));
		Assert.assertFalse(workflowService.nextStateIsPossible(resource, WorkflowState.DETECTED_NEW));
		Assert.assertFalse(workflowService.nextStateIsPossible(resource, WorkflowState.DETECTED_REVISION));
		Assert.assertFalse(workflowService.nextStateIsPossible(resource, WorkflowState.TECH_EVALUTATION_SUCCESS));
		Assert.assertFalse(workflowService.nextStateIsPossible(resource, WorkflowState.TECH_EVALUTATION_FAIL));
		
		final CKANResource revision = new CKANResource("id", "revision_id", false, "theParent");
		Assert.assertTrue(workflowService.nextStateIsPossible(revision, WorkflowState.DOWNLOADED));
		Assert.assertTrue(workflowService.nextStateIsPossible(revision, WorkflowState.OUTDATED));
		Assert.assertFalse(workflowService.nextStateIsPossible(revision, WorkflowState.DETECTED_NEW));
		Assert.assertFalse(workflowService.nextStateIsPossible(revision, WorkflowState.DETECTED_REVISION));
		Assert.assertFalse(workflowService.nextStateIsPossible(revision, WorkflowState.TECH_EVALUTATION_SUCCESS));
		Assert.assertFalse(workflowService.nextStateIsPossible(revision, WorkflowState.TECH_EVALUTATION_FAIL));
		
		revision.setWorkflowState(WorkflowState.DOWNLOADED);
		Assert.assertFalse(workflowService.nextStateIsPossible(revision, WorkflowState.DOWNLOADED));
		Assert.assertFalse(workflowService.nextStateIsPossible(revision, WorkflowState.OUTDATED));
		Assert.assertFalse(workflowService.nextStateIsPossible(revision, WorkflowState.DETECTED_NEW));
		Assert.assertFalse(workflowService.nextStateIsPossible(revision, WorkflowState.DETECTED_REVISION));
		Assert.assertTrue(workflowService.nextStateIsPossible(revision, WorkflowState.TECH_EVALUTATION_SUCCESS));
		Assert.assertTrue(workflowService.nextStateIsPossible(revision, WorkflowState.TECH_EVALUTATION_FAIL));
	}

}
