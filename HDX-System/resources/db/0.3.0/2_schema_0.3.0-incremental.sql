update ckan_resource set evaluator='SCRAPER_CONFIGURABLE' where evaluator='SCRAPER_VALIDATING';
update ckan_resource set importer='SCRAPER_CONFIGURABLE' where importer='SCRAPER_VALIDATING';

update ckan_dataset set type='SCRAPER_CONFIGURABLE' where type='SCRAPER_VALIDATING';

update ckan_resource set validationreport=null where importer='SCRAPER_CONFIGURABLE';
update ckan_resource set import_report=null where importer='SCRAPER_CONFIGURABLE';