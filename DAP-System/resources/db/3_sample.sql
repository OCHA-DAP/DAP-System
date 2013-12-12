INSERT INTO dap_user(id, ckanapikey, password) VALUES ('seustachi', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=');
 
INSERT INTO entity_type(id, code, name) VALUES (1, 'country', 'Country');
INSERT INTO entity_type(id, code, name) VALUES (2, 'crisis', 'Crisis');

ALTER SEQUENCE entity_type_seq RESTART WITH 3;
 
INSERT INTO entity(id, code, name, entity_type_id) VALUES (1, 'RUS', 'Russia', 1);
INSERT INTO entity(id, code, name, entity_type_id) VALUES (2, 'RWA', 'Rwanda', 1);
INSERT INTO entity(id, code, name, entity_type_id) VALUES (3, 'CMR', 'Cameroon', 1);
INSERT INTO entity(id, code, name, entity_type_id) VALUES (4, 'LUX', 'Luxembourg', 1);

ALTER SEQUENCE entity_seq RESTART WITH 5;

 
INSERT INTO indicator_type(id, code, name, unit) VALUES (1, 'PVX040', 'Incidence of conflict', 'Count');
INSERT INTO indicator_type(id, code, name, unit) VALUES (2, 'PSP080', 'Population Density', 'persons per square km');
INSERT INTO indicator_type(id, code, name, unit) VALUES (3, 'PSP010', 'Population (Total M+F)', 'persons');
INSERT INTO indicator_type(id, code, name, unit) VALUES (4, 'PSE030', 'GDP per capita, PPP', 'current international $');
INSERT INTO indicator_type(id, code, name, unit) VALUES (5, '_emdat:total_affected', 'Total affected', 'persons');
INSERT INTO indicator_type(id, code, name, unit) VALUES (6, 'PCX051', 'Mobile cellular subscriptions', 'per 100 inhabitants');
INSERT INTO indicator_type(id, code, name, unit) VALUES (7, 'PVF020', 'Per capita food supply', 'kcal/capita/day');

ALTER SEQUENCE indicator_type_seq RESTART WITH 8;

INSERT INTO source(id, code, name) VALUES (1, 'WB', 'World Bank');
INSERT INTO source(id, code, name) VALUES (2, 'acled', 'Armed Conflict Location and Event Dataset');
INSERT INTO source(id, code, name) VALUES (3, 'esa-unpd-WPP2012', 'esa-unpd-WPP2012');
INSERT INTO source(id, code, name) VALUES (4, 'emdat', 'emdat');
INSERT INTO source(id, code, name) VALUES (5, 'mdgs', 'mdgs');
INSERT INTO source(id, code, name) VALUES (6, 'faostat3', 'faostat3');

ALTER SEQUENCE source_seq RESTART WITH 7;
