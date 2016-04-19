/**
 * 
 */
package org.ocha.hdx.service;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

/**
 * @author alex
 *
 */
public class HDXServiceImpl_simple_Test {

	/**
	 * Test method for {@link org.ocha.hdx.service.HDXServiceImpl#hackTheURLProtocol(URL)}.
	 */
	@Test
	public void testHackTheURLProtocol() {
		try {
			HDXServiceImpl hdxServiceImpl1 = new ModifiedHDXServiceImpl("ckanhost.test", null, null, null);
			URL originalUrl1 = new URL("http://ckanhost.test/path/file?param=value#1");
			URL modifiedUrl1 = hdxServiceImpl1.hackTheURLProtocol(originalUrl1);
			assertTrue(originalUrl1.toString().equals(modifiedUrl1.toString()));
			
			assertTrue(urlsUseCorrectProtocol("http", hdxServiceImpl1.urlBaseForDatasetsList,
					hdxServiceImpl1.urlBaseForGroupsList, hdxServiceImpl1.urlBaseForDatasetContentV3,
					hdxServiceImpl1.urlBaseForGroupContentV3, hdxServiceImpl1.urlBaseForResourceCreation,
					hdxServiceImpl1.urlBaseForResourceShow));
			
			HDXServiceImpl hdxServiceImpl2 = new ModifiedHDXServiceImpl("ckanhost.test", false, null, null);
			URL originalUrl2 = new URL("http://ckanhost.test/path/file?param=value#1");
			URL modifiedUrl2 = hdxServiceImpl2.hackTheURLProtocol(originalUrl2);
			assertTrue(originalUrl2.toString().equals(modifiedUrl2.toString()));
			originalUrl2 = new URL("https://ckanhost.test/path/file?param=value#1");
			modifiedUrl2 = hdxServiceImpl2.hackTheURLProtocol(originalUrl2);
			assertTrue("HTTP should be forced", urlsDifferOnlyOnProtocol(originalUrl2, modifiedUrl2));
			
			originalUrl2 = new URL("https://testhost.test/path/file?param=value#1");
			modifiedUrl2 = hdxServiceImpl2.hackTheURLProtocol(originalUrl2);
			assertTrue("Non-ckan hosts should not be affected", originalUrl2.toString().equals(modifiedUrl2.toString()));
			
			assertTrue(urlsUseCorrectProtocol("http", hdxServiceImpl2.urlBaseForDatasetsList,
					hdxServiceImpl2.urlBaseForGroupsList, hdxServiceImpl2.urlBaseForDatasetContentV3,
					hdxServiceImpl2.urlBaseForGroupContentV3, hdxServiceImpl2.urlBaseForResourceCreation,
					hdxServiceImpl2.urlBaseForResourceShow));
			
			
			HDXServiceImpl hdxServiceImpl3 = new ModifiedHDXServiceImpl("ckanhost.test", true, null, null);
			URL originalUrl3 = new URL("https://ckanhost.test/path/file?param=value#1");
			URL modifiedUrl3 = hdxServiceImpl3.hackTheURLProtocol(originalUrl3);
			assertTrue(originalUrl3.toString().equals(modifiedUrl3.toString()));
			originalUrl3 = new URL("http://ckanhost.test/path/file?param=value#1");
			modifiedUrl3 = hdxServiceImpl3.hackTheURLProtocol(originalUrl3);
			assertTrue("HTTPS should be forced", urlsDifferOnlyOnProtocol(originalUrl3, modifiedUrl3));
			
			originalUrl3 = new URL("http://testhost.test/path/file?param=value#1");
			modifiedUrl3 = hdxServiceImpl3.hackTheURLProtocol(originalUrl3);
			assertTrue("Non-ckan hosts should not be affected", originalUrl3.toString().equals(modifiedUrl3.toString()));
			
			
			assertTrue(urlsUseCorrectProtocol("https", hdxServiceImpl3.urlBaseForDatasetsList,
					hdxServiceImpl3.urlBaseForGroupsList, hdxServiceImpl3.urlBaseForDatasetContentV3,
					hdxServiceImpl3.urlBaseForGroupContentV3, hdxServiceImpl3.urlBaseForResourceCreation,
					hdxServiceImpl3.urlBaseForResourceShow));
						
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			fail("Got exception: " + e.getMessage());
		}
	}
	
	private boolean urlsDifferOnlyOnProtocol(URL url1, URL url2){
		if ( !url1.getProtocol().equals(url2.getProtocol()) && url1.getHost().equals(url2.getHost()) 
				&& url1.getPort() == url2.getPort() && url1.getFile().equals(url2.getFile())) {
			return true;
		}
		return false;
	}
	
	private boolean urlsUseCorrectProtocol(String protocol, String... urls){
		String firstPart = protocol + ":";
		for (String url: urls){
			if (!url.startsWith(firstPart)) {
				return false;
			};
		}
		return true;
	}
	
	class ModifiedHDXServiceImpl extends HDXServiceImpl {

		@Override
		/**
		 * For testing purposes we don't want to verify that the directory exists
		 * @param stagingDirectory
		 */
		void verifyStagingDirectory(File stagingDirectory) {
			return;
		}

		public ModifiedHDXServiceImpl(String host, Boolean isHTTPS, String technicalAPIKey, File stagingDirectory) {
			super(host, isHTTPS, technicalAPIKey, stagingDirectory);
		}
		
	}

}
