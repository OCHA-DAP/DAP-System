/* The parent configuration entity */
INSERT INTO resource_configuration(id, name) VALUES(nextval('resource_configuration_seq'),'Sample Scraper Config');

/*
ALTER SEQUENCE resource_configuration_seq RESTART WITH 2;
*/
/* General configurations */
INSERT INTO resource_config_entry(id,resource_configuration_id,entry_key,entry_value)
	VALUES
	(nextval('resource_config_entry_seq'),currval('resource_configuration_seq'),'Minimum number of columns','6'),
	(nextval('resource_config_entry_seq'),currval('resource_configuration_seq'),'Allowed indicator type codes',	'_Access to electricity (% of population)&&_Children 1 year old immunized against measles, percentage&&_emdat:no_homeless&&_emdat:no_injured&&_emdat:total_affected&&_GNI, PPP (current international $)&&_HDR:68606&&_International migrant stock (% of population)&&_Internet users per 100 inhabitants&&_Land area (sq. km)&&_m49-name&&_Net ODA received per capita (current US$)&&_Number of infant deaths&&_Population undernourished, millions&&_Population undernourished, percentage&&_Population, total&&_unterm:ISO Country alpha-2-code&&_WPP2012_MORT_F02_CRUDE_DEATH_RATE&&CD010&&CD030&&CD050&&CD060&&CD070&&CD080&&CD090&&CG020&&CG030&&CG060&&CG070&&CG080&&CG100&&CG120&&CG140&&CG150&&CG260&&CG290&&CH070&&CH080&&CH090&&CH100&&PCH090&&PCX051&&PCX080&&PCX090&&PCX100&&PCX130&&PSE030&&PSE090&&PSE110&&PSE120&&PSE130&&PSE140&&PSE150&&PSE160&&PSE170&&PSE200&&PSE210&&PSE220&&PSP010&&PSP050&&PSP060&&PSP070&&PSP080&&PSP090&&PSP100&&PSP110&&PVE010&&PVE030&&PVE040&&PVE110&&PVE120&&PVE130&&PVF020&&PVH010&&PVH050&&PVH080&&PVH090&&PVH100&&PVH120&&PVH140&&PVH150&&PVH180&&PVH190&&PVL010&&PVL030&&PVL040&&PVN010&&PVN050&&PVW010&&PVW040&&PVX010&&PVX020&&PVX040&&PVX060&&PVX070');


/* Indicator type configurations */
SELECT 'START indicator_resource_config_entry' from text limit 0;
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value)  SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Access to electricity (% of population)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value)  SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Access to electricity (% of population)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value)  SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Min Value','0' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Access to electricity (% of population)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value)  SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Max Value','100' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Access to electricity (% of population)') AND lower(src.code)=lower('world-bank');

INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Children 1 year old immunized against measles, percentage') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Children 1 year old immunized against measles, percentage') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Min Value','0' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Children 1 year old immunized against measles, percentage') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Max Value','100' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Children 1 year old immunized against measles, percentage') AND lower(src.code)=lower('mdgs');

INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P1Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('_emdat:no_homeless') AND lower(src.code)=lower('emdat');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_emdat:no_homeless') AND lower(src.code)=lower('emdat');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P1Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('_emdat:no_injured') AND lower(src.code)=lower('emdat');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_emdat:no_injured') AND lower(src.code)=lower('emdat');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P1Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('_emdat:total_affected') AND lower(src.code)=lower('emdat');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_emdat:total_affected') AND lower(src.code)=lower('emdat');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_GNI, PPP (current international $)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_GNI, PPP (current international $)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Min Value','0' FROM indicator_type type,source src WHERE lower(type.code)=lower('_GNI, PPP (current international $)') AND lower(src.code)=lower('world-bank');


INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_HDR:68606') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_HDR:68606') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Min Value','0' FROM indicator_type type,source src WHERE lower(type.code)=lower('_HDR:68606') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Max Value','1' FROM indicator_type type,source src WHERE lower(type.code)=lower('_HDR:68606') AND lower(src.code)=lower('hdrstats');

INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_International migrant stock (% of population)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_International migrant stock (% of population)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Min Value','0' FROM indicator_type type,source src WHERE lower(type.code)=lower('_International migrant stock (% of population)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Max Value','100' FROM indicator_type type,source src WHERE lower(type.code)=lower('_International migrant stock (% of population)') AND lower(src.code)=lower('world-bank');

INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Internet users per 100 inhabitants') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Internet users per 100 inhabitants') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Min Value','0' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Internet users per 100 inhabitants') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Max Value','100' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Internet users per 100 inhabitants') AND lower(src.code)=lower('mdgs');

INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Land area (sq. km)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Land area (sq. km)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Min Value','0' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Land area (sq. km)') AND lower(src.code)=lower('world-bank');


INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('_m49-name') AND lower(src.code)=lower('m49');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('_m49-name') AND lower(src.code)=lower('m49');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Net ODA received per capita (current US$)') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Net ODA received per capita (current US$)') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Number of infant deaths') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Number of infant deaths') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Min Value','0' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Number of infant deaths') AND lower(src.code)=lower('world-bank');


INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Population undernourished, millions') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Population undernourished, millions') AND lower(src.code)=lower('mdgs');


INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'), currval('resource_configuration_seq'), type.id,src.id, 'Multiplication','millions' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Population undernourished, millions') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Population undernourished, percentage') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Population undernourished, percentage') AND lower(src.code)=lower('mdgs');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Population, total') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_Population, total') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Humanitarian_Bulletin') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Humanitarian_Bulletin') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Humanitarian_Dashboard') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Humanitarian_Dashboard') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Humanitarian_Snapshot') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Humanitarian_Snapshot') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Infographic') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Infographic') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Key_Messages') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Key_Messages') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Other') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Other') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Press_Release') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Press_Release') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Press_Review') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Press_Review') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Reference_Map') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Reference_Map') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Situation_Report') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Situation_Report') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Statement/Speech') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Statement/Speech') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Thematic_Map') AND lower(src.code)=lower('reliefweb-api');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_reliefweb_Thematic_Map') AND lower(src.code)=lower('reliefweb-api');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('_unterm:ISO Country alpha-2-code') AND lower(src.code)=lower('unterm');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('_unterm:ISO Country alpha-2-code') AND lower(src.code)=lower('unterm');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P5Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('_WPP2012_MORT_F02_CRUDE_DEATH_RATE') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('_WPP2012_MORT_F02_CRUDE_DEATH_RATE') AND lower(src.code)=lower('esa-unpd-wpp2012');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD010') AND lower(src.code)=lower('wikipedia');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD010') AND lower(src.code)=lower('wikipedia');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD030') AND lower(src.code)=lower('wikipedia');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD030') AND lower(src.code)=lower('wikipedia');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD050') AND lower(src.code)=lower('wikipedia');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD050') AND lower(src.code)=lower('wikipedia');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD060') AND lower(src.code)=lower('wikipedia');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD060') AND lower(src.code)=lower('wikipedia');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD070') AND lower(src.code)=lower('wikipedia');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD070') AND lower(src.code)=lower('wikipedia');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD080') AND lower(src.code)=lower('wikipedia');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD080') AND lower(src.code)=lower('wikipedia');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD090') AND lower(src.code)=lower('wikipedia');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CD090') AND lower(src.code)=lower('wikipedia');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG020') AND lower(src.code)=lower('unterm');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG020') AND lower(src.code)=lower('unterm');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG030') AND lower(src.code)=lower('unterm');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG030') AND lower(src.code)=lower('unterm');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG060') AND lower(src.code)=lower('unterm');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG060') AND lower(src.code)=lower('unterm');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG070') AND lower(src.code)=lower('unterm');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG070') AND lower(src.code)=lower('unterm');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG080') AND lower(src.code)=lower('unterm');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG080') AND lower(src.code)=lower('unterm');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG100') AND lower(src.code)=lower('unterm');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG100') AND lower(src.code)=lower('unterm');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG120') AND lower(src.code)=lower('unterm');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG120') AND lower(src.code)=lower('unterm');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG140') AND lower(src.code)=lower('unterm');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG140') AND lower(src.code)=lower('unterm');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG150') AND lower(src.code)=lower('worldbank-lending-groups');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG150') AND lower(src.code)=lower('worldbank-lending-groups');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG260') AND lower(src.code)=lower('accuweather');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG260') AND lower(src.code)=lower('accuweather');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG290') AND lower(src.code)=lower('unterm');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','none' FROM indicator_type type,source src WHERE lower(type.code)=lower('CG290') AND lower(src.code)=lower('unterm');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P1Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('CH070') AND lower(src.code)=lower('emdat');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('CH070') AND lower(src.code)=lower('emdat');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P1Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('CH080') AND lower(src.code)=lower('emdat');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('CH080') AND lower(src.code)=lower('emdat');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P1Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('CH090') AND lower(src.code)=lower('emdat');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('CH090') AND lower(src.code)=lower('emdat');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P1Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('CH100') AND lower(src.code)=lower('emdat');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('CH100') AND lower(src.code)=lower('emdat');


INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'), currval('resource_configuration_seq'), type.id,src.id, 'Multiplication','thousands' FROM indicator_type type,source src WHERE lower(type.code)=lower('CH100') AND lower(src.code)=lower('emdat');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCH090') AND lower(src.code)=lower('unicef-infobycountry');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCH090') AND lower(src.code)=lower('unicef-infobycountry');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCX051') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCX051') AND lower(src.code)=lower('mdgs');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCX080') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCX080') AND lower(src.code)=lower('mdgs');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCX090') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCX090') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCX100') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCX100') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCX130') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PCX130') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE030') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE030') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE090') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE090') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE110') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE110') AND lower(src.code)=lower('hdrstats');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE120') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE120') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE130') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE130') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE140') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE140') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE150') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE150') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE160') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE160') AND lower(src.code)=lower('hdrstats');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE170') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE170') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE200') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE200') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE210') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE210') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Min Value','0' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE210') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Max Value','100' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE210') AND lower(src.code)=lower('world-bank');

INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE220') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSE220') AND lower(src.code)=lower('hdrstats');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP010') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP010') AND lower(src.code)=lower('esa-unpd-wpp2012');


INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'), currval('resource_configuration_seq'), type.id,src.id, 'Multiplication','thousands' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP010') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P5Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP050') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP050') AND lower(src.code)=lower('esa-unpd-wpp2012');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP060') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP060') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP070') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP070') AND lower(src.code)=lower('esa-unpd-wpp2012');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP080') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP080') AND lower(src.code)=lower('esa-unpd-wpp2012');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP090') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP090') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP100') AND lower(src.code)=lower('esa-unpd-wup2011');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP100') AND lower(src.code)=lower('esa-unpd-wup2011');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP110') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PSP110') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE010') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE010') AND lower(src.code)=lower('hdrstats');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE030') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE030') AND lower(src.code)=lower('hdrstats');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE040') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE040') AND lower(src.code)=lower('hdrstats');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE110') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE110') AND lower(src.code)=lower('hdrstats');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE120') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE120') AND lower(src.code)=lower('hdrstats');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE130') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVE130') AND lower(src.code)=lower('mdgs');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVF020') AND lower(src.code)=lower('faostat3');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVF020') AND lower(src.code)=lower('faostat3');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P5Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH010') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH010') AND lower(src.code)=lower('esa-unpd-wpp2012');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P5Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH050') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH050') AND lower(src.code)=lower('esa-unpd-wpp2012');


INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'), currval('resource_configuration_seq'), type.id,src.id, 'Multiplication','thousands' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH050') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH080') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH080') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH090') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH090') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P5Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH100') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH100') AND lower(src.code)=lower('esa-unpd-wpp2012');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH120') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH120') AND lower(src.code)=lower('hdrstats');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH140') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH140') AND lower(src.code)=lower('mdgs');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P5Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH150') AND lower(src.code)=lower('esa-unpd-wpp2012');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH150') AND lower(src.code)=lower('esa-unpd-wpp2012');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH180') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH180') AND lower(src.code)=lower('hdrstats');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH190') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVH190') AND lower(src.code)=lower('mdgs');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVL010') AND lower(src.code)=lower('worldaerodata');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','yyyy-MM-dd' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVL010') AND lower(src.code)=lower('worldaerodata');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVL030') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVL030') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVL040') AND lower(src.code)=lower('world-bank');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVL040') AND lower(src.code)=lower('world-bank');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY/P3Y' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVN010') AND lower(src.code)=lower('fao-foodsec');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVN010') AND lower(src.code)=lower('fao-foodsec');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVN050') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVN050') AND lower(src.code)=lower('mdgs');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVW010') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVW010') AND lower(src.code)=lower('mdgs');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVW040') AND lower(src.code)=lower('mdgs');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVW040') AND lower(src.code)=lower('mdgs');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVX010') AND lower(src.code)=lower('echo');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVX010') AND lower(src.code)=lower('echo');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVX020') AND lower(src.code)=lower('echo');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVX020') AND lower(src.code)=lower('echo');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVX040') AND lower(src.code)=lower('acled');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVX040') AND lower(src.code)=lower('acled');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVX060') AND lower(src.code)=lower('hdi-disaster');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVX060') AND lower(src.code)=lower('hdi-disaster');



INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected time format','YYYY' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVX070') AND lower(src.code)=lower('hdrstats');
INSERT INTO indicator_resource_config_entry(id,resource_configuration_id,indicator_type_id,source_id,entry_key,entry_value) SELECT nextval('indicator_resource_config_entry_seq'),currval('resource_configuration_seq'),type.id,src.id,'Expected start time format','YYYY-01-01' FROM indicator_type type,source src WHERE lower(type.code)=lower('PVX070') AND lower(src.code)=lower('hdrstats');

SELECT 'END indicator_resource_config_entry' from text limit 0;


