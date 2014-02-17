INSERT INTO hdx_user(id, ckanapikey, password, role) VALUES ('seustachi', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=', 'admin');
INSERT INTO hdx_user(id, ckanapikey, password, role) VALUES ('cjhendrix', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=', 'api');
INSERT INTO hdx_user(id, ckanapikey, password, role) VALUES ('bmi', 'KHPUpxraF+oA1wkmG+s0hQ==', '{SHA}JuQIzlXAzjNi9ovRIG1IfdWw9k0=', 'admin');

INSERT INTO text(id, default_value) VALUES (28, 'Country');
INSERT INTO entity_type(id, code, text_id) VALUES (1, 'country', 28);

INSERT INTO text(id, default_value) VALUES (29, 'Crisis');
INSERT INTO entity_type(id, code, text_id) VALUES (2, 'crisis', 29);

ALTER SEQUENCE entity_type_seq RESTART WITH 3;

INSERT INTO text(id, default_value) VALUES (1, 'Russia');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (1, 'RUS', 1, 1);
INSERT INTO text(id, default_value) VALUES (2, 'Rwanda');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (2, 'RWA', 2, 1);
INSERT INTO text(id, default_value) VALUES (3, 'Cameroon');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (3, 'CMR', 3, 1);
INSERT INTO text(id, default_value) VALUES (4, 'Luxembourg');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (4, 'LUX', 4, 1);

ALTER SEQUENCE entity_seq RESTART WITH 5;

INSERT INTO language(code, native_name) VALUES ('FR', 'Français');
INSERT INTO language(code, native_name) VALUES ('EN', 'English');

INSERT INTO hdx_translation(text, language, value) VALUES (1, 'FR', 'Russie');
INSERT INTO hdx_translation(text, language, value) VALUES (1, 'EN', 'Russia');
INSERT INTO hdx_translation(text, language, value) VALUES (4, 'FR', 'Luxembourg');
INSERT INTO hdx_translation(text, language, value) VALUES (4, 'EN', 'Luxemburg');


INSERT INTO text(id, default_value) VALUES (5, 'Incidence of conflict');
INSERT INTO indicator_type(id, code, text_id, unit, value_type) VALUES (1, 'PVX040', 5, 'Count', 'NUMBER');
INSERT INTO text(id, default_value) VALUES (6, 'Population Density');
INSERT INTO indicator_type(id, code, text_id, unit, value_type) VALUES (2, 'PSP080', 6, 'persons per square km', 'NUMBER');
INSERT INTO text(id, default_value) VALUES (7, 'Population (Total M+F)');
INSERT INTO indicator_type(id, code, text_id, unit, value_type) VALUES (3, 'PSP010', 7, 'persons', 'NUMBER');
INSERT INTO text(id, default_value) VALUES (8, 'GDP per capita, PPP');
INSERT INTO indicator_type(id, code, text_id, unit, value_type) VALUES (4, 'PSE030', 8, 'current international $', 'NUMBER');
INSERT INTO text(id, default_value) VALUES (9, 'Total affected');
INSERT INTO indicator_type(id, code, text_id, unit, value_type) VALUES (5, '_emdat:total_affected', 9, 'persons', 'NUMBER');
INSERT INTO text(id, default_value) VALUES (10, 'Mobile cellular subscriptions');
INSERT INTO indicator_type(id, code, text_id, unit, value_type) VALUES (6, 'PCX051', 10, 'per 100 inhabitants', 'NUMBER');
INSERT INTO text(id, default_value) VALUES (11, 'Per capita food supply');
INSERT INTO indicator_type(id, code, text_id, unit, value_type) VALUES (7, 'PVF020', 11, 'kcal/capita/day', 'NUMBER');

ALTER SEQUENCE indicator_type_seq RESTART WITH 8;

INSERT INTO text(id, default_value) VALUES (12, 'World Bank');
INSERT INTO text(id, default_value) VALUES (13, 'WB');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES (1, 'http://www.worldbank.org/', 12, 13);
INSERT INTO text(id, default_value) VALUES (14, 'United Nations Development Program');
INSERT INTO text(id, default_value) VALUES (15, 'UNDP');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES (2, 'http://www.undp.org', 14, 15);
INSERT INTO text(id, default_value) VALUES (16, 'Centre for Research on the Epidemiology of Disasters');
INSERT INTO text(id, default_value) VALUES (17, 'CRED');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES (3, 'http://www.cred.be/', 16, 17);
INSERT INTO text(id, default_value) VALUES (18, 'UN Department of Economic and Social Affairs');
INSERT INTO text(id, default_value) VALUES (19, 'UNDESA');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES (4, 'http://www.un.org/desa', 18, 19);
INSERT INTO text(id, default_value) VALUES (20, 'Food and Agriculture Organization of the United Nations');
INSERT INTO text(id, default_value) VALUES (21, 'FAO');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES (5, 'http://www.fao.org/', 20, 21);

ALTER SEQUENCE organisation_seq RESTART WITH 6;

INSERT INTO text(id, default_value) VALUES (22, 'World Bank');
INSERT INTO source(id, code, text_id, organisation_id) VALUES (1, 'WB', 12, 1);
INSERT INTO text(id, default_value) VALUES (23, 'Armed Conflict Location and Event Dataset');
INSERT INTO source(id, code, text_id) VALUES (2, 'acled', 13);
INSERT INTO text(id, default_value) VALUES (24, 'esa-unpd-WPP2012');
INSERT INTO source(id, code, text_id, organisation_id) VALUES (3, 'esa-unpd-WPP2012', 14, 2);
INSERT INTO text(id, default_value) VALUES (25, 'emdat');
INSERT INTO source(id, code, text_id,organisation_id) VALUES (4, 'emdat', 15, 3);
INSERT INTO text(id, default_value) VALUES (26, 'mdgs');
INSERT INTO source(id, code, text_id, organisation_id) VALUES (5, 'mdgs', 16, 4);
INSERT INTO text(id, default_value) VALUES (27, 'faostat3');
INSERT INTO source(id, code, text_id, organisation_id) VALUES (6, 'faostat3', 17, 5);

ALTER SEQUENCE source_seq RESTART WITH 7;
ALTER SEQUENCE text_seq RESTART WITH 30;
