ALTER TABLE hdx_indicator ADD COLUMN validation_status character varying(255) not null Default 'SUCCESS';

ALTER TABLE hdx_indicator ADD COLUMN expected_time_format varchar(255);
ALTER TABLE hdx_indicator ADD COLUMN interpreted_time_format varchar(255);
ALTER TABLE hdx_indicator ADD COLUMN lower_boundary float8;
ALTER TABLE hdx_indicator ADD COLUMN multiplier float8;
ALTER TABLE hdx_indicator ADD COLUMN upper_boundary float8;
ALTER TABLE hdx_indicator ADD COLUMN validation_message varchar(255);