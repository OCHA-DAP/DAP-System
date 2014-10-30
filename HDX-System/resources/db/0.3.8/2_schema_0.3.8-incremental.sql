ALTER TABLE ckan_resource DROP COLUMN import_report;
ALTER TABLE ckan_resource DROP COLUMN validationReport;

ALTER TABLE ckan_resource ADD COLUMN type varchar(255);