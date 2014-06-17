package org.ocha.hdx.persistence.entity.curateddata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hdx_view_indicator_type_count")
public class IndicatorTypeCount {

	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "code", unique = true, nullable = false, updatable = false)
	private String code;

	@Column(name = "count", nullable = false, updatable = false)
	private Integer count;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(final Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "IndicatorTypeCount [id=" + id + ", code=" + code + ", count=" + count + "]";
	}

}