package org.ocha.dap.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONBuilderWrapper {
	
	public static Gson getGSON(){
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls().setDateFormat("yyyyMMdd'T'HH:mm:ss'.'SSSZ");
		return gsonBuilder.create();
	}

}
