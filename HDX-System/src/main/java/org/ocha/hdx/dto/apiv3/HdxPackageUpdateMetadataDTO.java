package org.ocha.hdx.dto.apiv3;

import java.util.ArrayList;
import java.util.List;

public class HdxPackageUpdateMetadataDTO {

	public HdxPackageUpdateMetadataDTO() {
		super();
		groups.add(new GroupV3DTO("world"));
	}

	// The uuid of the ckan dataset we want to update
	private String id;

	// The name of the ckan dataset we want to update
	private String name;

	// When the DATA was updated the last time (i.e last run of Import)
	private String last_data_update_date;

	// When the METADATA was updated the last time
	private String last_metadata_update_date;

	// Organization name
	private String dataset_source;

	private String dataset_source_short_name;

	// sourceCode
	private String source_code;

	// indicator type name
	private String indicator_type;

	// indicator type code
	private String indicator_type_code;

	// range of the data, format : 11/02/2014-11/20/2014
	private String dataset_date;

	private String description;
	private String methodology;
	private String more_info;
	private String terms_of_use;
	private String validation_notes_and_comments;

	private List<GroupV3DTO> groups = new ArrayList<GroupV3DTO>();

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getLast_data_update_date() {
		return last_data_update_date;
	}

	public void setLast_data_update_date(final String last_data_update_date) {
		this.last_data_update_date = last_data_update_date;
	}

	public String getLast_metadata_update_date() {
		return last_metadata_update_date;
	}

	public void setLast_metadata_update_date(final String last_metadata_update_date) {
		this.last_metadata_update_date = last_metadata_update_date;
	}

	public String getDataset_source() {
		return dataset_source;
	}

	public void setDataset_source(final String dataset_source) {
		this.dataset_source = dataset_source;
	}

	public String getDataset_source_short_name() {
		return dataset_source_short_name;
	}

	public void setDataset_source_short_name(final String dataset_source_short_name) {
		this.dataset_source_short_name = dataset_source_short_name;
	}

	public String getSource_code() {
		return source_code;
	}

	public void setSource_code(final String source_code) {
		this.source_code = source_code;
	}

	public String getIndicator_type() {
		return indicator_type;
	}

	public void setIndicator_type(final String indicator_type) {
		this.indicator_type = indicator_type;
	}

	public String getIndicator_type_code() {
		return indicator_type_code;
	}

	public void setIndicator_type_code(final String indicator_type_code) {
		this.indicator_type_code = indicator_type_code;
	}

	public String getDataset_date() {
		return dataset_date;
	}

	public void setDataset_date(final String dataset_date) {
		this.dataset_date = dataset_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getMethodology() {
		return methodology;
	}

	public void setMethodology(final String methodology) {
		this.methodology = methodology;
	}

	public String getMore_info() {
		return more_info;
	}

	public void setMore_info(final String more_info) {
		this.more_info = more_info;
	}

	public String getTerms_of_use() {
		return terms_of_use;
	}

	public void setTerms_of_use(final String terms_of_use) {
		this.terms_of_use = terms_of_use;
	}

	public String getValidation_notes_and_comments() {
		return validation_notes_and_comments;
	}

	public void setValidation_notes_and_comments(final String validation_notes_and_comments) {
		this.validation_notes_and_comments = validation_notes_and_comments;
	}

	public List<GroupV3DTO> getGroups() {
		return groups;
	}

	public void setGroups(final List<GroupV3DTO> groups) {
		this.groups = groups;
	}

	public void addGroup(final String countryCode) {

		groups.add(new GroupV3DTO(countryCode));
	}
}
