package org.ocha.dap.persistence.entity.i18n;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "text")
@SequenceGenerator(name = "text_seq", sequenceName = "text_seq")
public class Text {

	public Text() {
		super();
	}

	public Text(String defaultValue) {
		super();
		this.defaultValue = defaultValue;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "text_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "default_value", nullable = false, updatable = true)
	private String defaultValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
