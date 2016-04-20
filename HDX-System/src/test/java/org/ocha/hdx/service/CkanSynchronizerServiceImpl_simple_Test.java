package org.ocha.hdx.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class CkanSynchronizerServiceImpl_simple_Test {

	@Test
	public void testCkanSynchronizerServiceImpl() {
		CkanSynchronizerServiceImpl service1 = new CkanSynchronizerServiceImpl("ckanhost.test", false, null);
		assertTrue(service1.urlBaseForHdxPackageUpdate.startsWith("http:"));
		
		CkanSynchronizerServiceImpl service2 = new CkanSynchronizerServiceImpl("ckanhost.test", true, null);
		assertTrue(service2.urlBaseForHdxPackageUpdate.startsWith("https:"));
	}

}
