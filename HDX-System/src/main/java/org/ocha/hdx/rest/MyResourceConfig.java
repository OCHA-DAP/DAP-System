package org.ocha.hdx.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class MyResourceConfig extends ResourceConfig {

	public MyResourceConfig() {
		super();
		register(AuthenticationFilter.class);
		register(RolesAllowedDynamicFeature.class);

		register(AdminResource.class);
		register(LoginResource.class);
		register(APIResource.class);

		register(AuthenticationExceptionMapper.class);
		register(InsufficientCredentialsExceptionMapper.class);
	}

}
