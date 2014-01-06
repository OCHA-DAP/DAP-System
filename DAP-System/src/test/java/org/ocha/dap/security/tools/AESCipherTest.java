package org.ocha.dap.security.tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml" })
public class AESCipherTest {

	@Autowired
	private AESCipher aesCipher;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEncryptAndDecrypt() throws Exception {
		final String plainText = "secret4ocha!";
		final String encrypted = aesCipher.encrypt(plainText);
		assertTrue(!plainText.equals(encrypted));

		final String decrypted = aesCipher.decrypt(encrypted);
		assertEquals(plainText, decrypted);
	}

}
