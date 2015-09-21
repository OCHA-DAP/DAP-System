ALTER TABLE ckan_resource
   ALTER COLUMN parentdataset_revision_timestamp DROP NOT NULL;
   
ALTER TABLE ckan_resource
   ALTER COLUMN revision_timestamp DROP NOT NULL;