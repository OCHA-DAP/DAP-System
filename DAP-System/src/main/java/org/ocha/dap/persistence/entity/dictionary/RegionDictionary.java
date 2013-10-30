package org.ocha.dap.persistence.entity.dictionary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region_dictionary")
public class RegionDictionary {

	@Id
	private final String unnormalized_name;

	// FIXME it will probably be a composite key, (unnormalized_name, source) as
	// the same unnormalized_name can refer to different regions
	// for different sources

	private final String normalized_region;

	public RegionDictionary(final String unnormalized_name, final String normalized_region) {
		super();
		this.unnormalized_name = unnormalized_name;
		this.normalized_region = normalized_region;
	}

	public String getUnnormalized_name() {
		return unnormalized_name;
	}

	public String getNormalized_region() {
		return normalized_region;
	}

}
