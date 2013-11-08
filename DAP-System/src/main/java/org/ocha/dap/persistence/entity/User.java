package org.ocha.dap.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dap_user")
public class User {
	
	@Id
	private final String id;
	
	@Column(name = "password", updatable = true)
	private final String password;
	
	@Column(name = "ckanApiKey", updatable = true)
	private final String ckanApiKey;
	
	public User(final String id, final String password, final String ckanApiKey) {
		super();
		this.id = id;
		this.password = password;
		this.ckanApiKey = ckanApiKey;
	}

	// this constructor is here for hibernate (used via reflection)
	@SuppressWarnings("unused")
	private User() {
		super();
		this.id = null;
		this.password = null;
		this.ckanApiKey = null;
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
