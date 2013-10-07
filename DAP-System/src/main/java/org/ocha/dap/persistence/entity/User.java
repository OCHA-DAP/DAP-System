package org.ocha.dap.persistence.entity;

public class User {
	
	private final String id;
	private final String password;
	private final String ckanApiKey;
	
	public User(final String id, final String password, final String ckanApiKey) {
		super();
		this.id = id;
		this.password = password;
		this.ckanApiKey = ckanApiKey;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getCkanApiKey() {
		return ckanApiKey;
	}
	
	

}
