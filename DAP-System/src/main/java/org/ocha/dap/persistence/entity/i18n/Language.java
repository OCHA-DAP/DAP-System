package org.ocha.dap.persistence.entity.i18n;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "language")
public class Language {

	@Id
	private String code;

	@Column(name = "native_name", nullable = false, updatable = false)
	private String nativeName;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "id.language")
	private List<Translation> translations;

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(final String nativeName) {
		this.nativeName = nativeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((nativeName == null) ? 0 : nativeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Language other = (Language) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (nativeName == null) {
			if (other.nativeName != null)
				return false;
		} else if (!nativeName.equals(other.nativeName))
			return false;
		return true;
	}

}
