package org.ocha.dap.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class MyResourceConfig extends ResourceConfig {

	public MyResourceConfig() {
		super();
		register(AuthenticationFilter.class);
		register(RolesAllowedDynamicFeature.class);
	}

}
