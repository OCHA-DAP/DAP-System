INSERT INTO dap_user(id, ckanapikey, password) VALUES ('seustachi', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=');
 
INSERT INTO entity_type(id, code, name) VALUES (1, 'country', 'Country');
INSERT INTO entity_type(id, code, name) VALUES (2, 'crisis', 'Crisis');
 
INSERT INTO entity(id, code, name, entity_type_id) VALUES (3, 'RUS', 'Russia', 1);
INSERT INTO entity(id, code, name, entity_type_id) VALUES (4, 'RWA', 'Rwanda', 1);
INSERT INTO entity(id, code, name, entity_type_id) VALUES (5, 'CMR', 'Cameroon', 1);
INSERT INTO entity(id, code, name, entity_type_id) VALUES (6, 'LUX', 'Luxembourg', 1);

 
INSERT INTO indicator_type(id, code, name) VALUES (7, 'population', 'Population');
INSERT INTO indicator_type(id, code, name, unit) VALUES (8, 'PVX040', 'Incidence of conflict', 'Count');
INSERT INTO indicator_type(id, code, name, unit) VALUES (9, 'PSP080', 'Population Density', 'persons per square km');
INSERT INTO indicator_type(id, code, name, unit) VALUES (10, 'PSP010', 'Population (Total M+F)', 'persons');
INSERT INTO indicator_type(id, code, name, unit) VALUES (11, 'PSE030', 'GDP per capita, PPP', 'current international $');
INSERT INTO indicator_type(id, code, name, unit) VALUES (12, '_emdat:total_affected', 'Total affected', 'persons');


INSERT INTO source(id, code, name) VALUES (13, 'WB', 'World Bank');
INSERT INTO source(id, code, name) VALUES (14, 'acled', 'Armed Conflict Location and Event Dataset');
INSERT INTO source(id, code, name) VALUES (15, 'esa-unpd-WPP2012', 'esa-unpd-WPP2012');
INSERT INTO source(id, code, name) VALUES (16, 'emdat', 'emdat');
emdat



ALTER SEQUENCE hibernate_sequence RESTART WITH 17;
