package org.ocha.dap.service;

import java.util.List;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ocha.dap.dto.DatasetDTO;
import org.ocha.dap.dto.DatasetListDTO;
import org.ocha.dap.persistence.dao.UserDAO;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.security.exception.InsufficientCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DAPServiceImpl implements DAPService {

	private static final Logger log = LoggerFactory.getLogger(DAPServiceImpl.class);

	private final String urlBaseForDatasetsList;
	private final String urlBaseForDatasetContent;
	private final String technicalAPIKey;

	public DAPServiceImpl(final String host, final String technicalAPIKey) {
		super();
		this.urlBaseForDatasetsList = String.format("http://%s/api/3/action/package_list", host);
		this.urlBaseForDatasetContent = String.format("http://%s/api/3/action/package_show?id=", host);
		this.technicalAPIKey = technicalAPIKey;
	}

	@Autowired
	private UserDAO userDao;

	@Override
	public List<String> getDatasetsListFromCKAN(final String userId) throws InsufficientCredentialsException {
		final String apiKey = userDao.getUserApiKey(userId);
		return getDatasetListDTOFromQuery(apiKey);
	}

	@Override
	public DatasetDTO getDatasetContentFromCKAN(final String userId, final String datasetName) throws InsufficientCredentialsException {
		final String apiKey = userDao.getUserApiKey(userId);

		return getDatasetDTOFromQuery(datasetName, apiKey, null);

	}

	List<String> getDatasetListDTOFromQuery(final String apiKey) {
		final String jsonResult = performHttpCallAndGetResponse(urlBaseForDatasetsList, apiKey, null);
		if (jsonResult == null) {
			return null;
		} else {
			final GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("yyyyMMdd'T'HH:mm:ss'.'SSSZ");
			final Gson gson = gsonBuilder.create();

			final DatasetListDTO returnedValue =  gson.fromJson(jsonResult, DatasetListDTO.class);
			return returnedValue.getResult();
		}
	}

	@Override
	public boolean authenticate(final String id, final String password) throws AuthenticationException {
		return userDao.authenticate(id, password);
	}

	DatasetDTO getDatasetDTOFromQuery(final String datasetName, final String apiKey, final String query) {
		final String urlForDataSet = String.format("%s%s", urlBaseForDatasetContent, datasetName);
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
