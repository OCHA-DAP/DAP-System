/**
 *
 */
package org.ocha.hdx.persistence.entity.configs;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Index;

/**
 * @author alexandru-m-g
 *
 */
@MappedSuperclass
public abstract class AbstractConfigEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "entry_key", unique = false, nullable = false, updatable = false)
	@Index(name = "keyIndex")
	private String entryKey;

	@Column(name = "entry_value", unique = false, nullable = false, updatable = true)
	private String entryValue;

	public AbstractConfigEntry() {}

	public AbstractConfigEntry(final String key, final String value) {
		super();
		this.entryKey = key;
		this.entryValue = value;
	}

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getEntryKey() {
		return this.entryKey;
	}

	public void setEntryKey(final String key) {
		this.entryKey = key;
	}

	public String getEntryValue() {
		return this.entryValue;
	}

	public void setEntryValue(final String value) {
		this.entryValue = value;
	}



}
