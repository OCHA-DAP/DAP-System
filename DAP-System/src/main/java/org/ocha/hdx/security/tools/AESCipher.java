package org.ocha.hdx.security.tools;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Base64;

/**
 * A simple text cipher to encrypt/decrypt a string.
 * 
 * @author seustachi
 */
public class AESCipher {

	private SecretKey key;
	private Cipher cipher;

	// secret key length must be 16
	public AESCipher(final String secret) {
		super();
		try {
			key = new SecretKeySpec(secret.getBytes(), "AES");
			cipher = Cipher.getInstance("AES", "SunJCE");
		} catch (final Throwable t) {
			t.printStackTrace();
		}
	}

	public synchronized String encrypt(final String plainText) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key);
		final byte[] cipherText = cipher.doFinal(plainText.getBytes());
		return new String(Base64.encode(cipherText));
	}

	public synchronized String decrypt(final String codedText) throws Exception {
		final byte[] encypted = Base64.decode(codedText.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, key);
		final byte[] decrypted = cipher.doFinal(encypted);
		return new String(decrypted);
	}

}