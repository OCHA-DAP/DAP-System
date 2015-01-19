package org.ocha.hdx.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONBuilderWrapper {

	public static Gson getGSON() {
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSSSS");
		gsonBuilder.enableComplexMapKeySerialization();
		return gsonBuilder.create();
	}

	public static Gson getGSONIgnoreNulls() {
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSSSS");
		gsonBuilder.enableComplexMapKeySerialization();
		return gsonBuilder.create();
	}

}
