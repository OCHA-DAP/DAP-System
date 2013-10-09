package org.ocha.dap.jobs;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ocha.dap.dto.DatasetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CKANPollingClient implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(CKANPollingClient.class);

	private final String urlBaseForDataSet;
	private final String technicalAPIKey;

	public CKANPollingClient(final String host, final String technicalAPIKey) {
		super();
		this.urlBaseForDataSet = String.format("http://%s/api/3/action/package_show?id=", host);
		this.technicalAPIKey = technicalAPIKey;
	}

	@Override
	@Scheduled(fixedDelay = 100000, initialDelay = 100000)
	public void run() {
		try {
			// for now, just a simple get, no JSON POST
			// FIXME we should have the name of the dataset here, either static
			// or
			// discovered
			final DatasetDTO dto = getDatasetDTOFromQuery(null, technicalAPIKey, null);
			// FIXME do something with it
		} catch (final Throwable e) {
			log.error(e.toString(), e);
		}
	}

	DatasetDTO getDatasetDTOFromQuery(final String dataSetName, final String apiKey, final String query) {
		final String urlForDataSet = String.format("%s%s", urlBaseForDataSet, dataSetName);
		final String jsonResult = performHttpCallAndGetResponse(urlForDataSet, apiKey, query);
		if (jsonResult == null) {
			return null;
		} else {
			final GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("yyyyMMdd'T'HH:mm:ss'.'SSSZ");
			final Gson gson = gsonBuilder.create();

			return gson.fromJson(jsonResult, DatasetDTO.class);
		}
	}

	private String performHttpCallAndGetResponse(final String url, final String apiKey, final String query) {
		String responseBody = null;
		final DefaultHttpClient httpclient = new DefaultHttpClient();

		// final HttpPost httpPost = new HttpPost(urlString);
		final HttpGet httpGet = new HttpGet(url);
		try {

			// final StringEntity se = new StringEntity(query);
			// httpPost.setEntity(se);

			// se.setContentType("text/xml");
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("accept", "application/json");

			if (apiKey != null) {
				httpGet.addHeader("X-CKAN-API-Key", apiKey);
			}

			// log.debug("about to send query: " + query);

			final ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = httpclient.execute(httpGet, responseHandler);
		} catch (final Exception e) {
			log.debug(e.toString(), e);
		}

		return responseBody;

	}

}
