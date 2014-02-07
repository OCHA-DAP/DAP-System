package org.ocha.hdx.persistence.entity;

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
	private String password;

	@Column(name = "role", updatable = true)
	private String role;

	@Column(name = "ckanApiKey", updatable = true)
	private String ckanApiKey;

	public User(final String id, final String password, final String role, final String ckanApiKey) {
		super();
		this.id = id;
		setRole(role);
		setPassword(password);
		setCkanApiKey(ckanApiKey);
	}

	// this constructor is here for hibernate (used via reflection)
	@SuppressWarnings("unused")
	private User() {
		super();
		id = null;
		setRole(null);
		setPassword(null);
		setCkanApiKey(null);
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getCkanApiKey() {
		return ckanApiKey;
	}

	public void setCkanApiKey(final String ckanApiKey) {
		this.ckanApiKey = ckanApiKey;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
