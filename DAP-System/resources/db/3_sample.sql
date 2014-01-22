INSERT INTO dap_user(id, ckanapikey, password, role) VALUES ('seustachi', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=', 'admin');
INSERT INTO dap_user(id, ckanapikey, password, role) VALUES ('cjhendrix', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=', 'api');
INSERT INTO dap_user(id, ckanapikey, password, role) VALUES ('bmi', 'KHPUpxraF+oA1wkmG+s0hQ==', '{SHA}JuQIzlXAzjNi9ovRIG1IfdWw9k0=', 'admin');

INSERT INTO entity_type(id, code, name) VALUES (1, 'country', 'Country');
INSERT INTO entity_type(id, code, name) VALUES (2, 'crisis', 'Crisis');

ALTER SEQUENCE entity_type_seq RESTART WITH 3;

INSERT INTO text(id, default_value) VALUES (1, 'Russia');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (1, 'RUS', 1, 1);
INSERT INTO text(id, default_value) VALUES (2, 'Rwanda');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (2, 'RWA', 2, 1);
INSERT INTO text(id, default_value) VALUES (3, 'Cameroon');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (3, 'CMR', 3, 1);
INSERT INTO text(id, default_value) VALUES (4, 'Luxembourg');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (4, 'LUX', 4, 1);

ALTER SEQUENCE text_seq RESTART WITH 5;
ALTER SEQUENCE entity_seq RESTART WITH 5;

INSERT INTO language(code, native_name) VALUES ('FR', 'Français');
INSERT INTO language(code, native_name) VALUES ('EN', 'English');

INSERT INTO dap_translation(text, language, value) VALUES (1, 'FR', 'Russie');
INSERT INTO dap_translation(text, language, value) VALUES (1, 'EN', 'Russia');
INSERT INTO dap_translation(text, language, value) VALUES (4, 'FR', 'Luxembourg');
INSERT INTO dap_translation(text, language, value) VALUES (4, 'EN', 'Luxemburg');

 
INSERT INTO indicator_type(id, code, name, unit, value_type) VALUES (1, 'PVX040', 'Incidence of conflict', 'Count', 'NUMBER');
INSERT INTO indicator_type(id, code, name, unit, value_type) VALUES (2, 'PSP080', 'Population Density', 'persons per square km', 'NUMBER');
INSERT INTO indicator_type(id, code, name, unit, value_type) VALUES (3, 'PSP010', 'Population (Total M+F)', 'persons', 'NUMBER');
INSERT INTO indicator_type(id, code, name, unit, value_type) VALUES (4, 'PSE030', 'GDP per capita, PPP', 'current international $', 'NUMBER');
INSERT INTO indicator_type(id, code, name, unit, value_type) VALUES (5, '_emdat:total_affected', 'Total affected', 'persons', 'NUMBER');
INSERT INTO indicator_type(id, code, name, unit, value_type) VALUES (6, 'PCX051', 'Mobile cellular subscriptions', 'per 100 inhabitants', 'NUMBER');
INSERT INTO indicator_type(id, code, name, unit, value_type) VALUES (7, 'PVF020', 'Per capita food supply', 'kcal/capita/day', 'NUMBER');

ALTER SEQUENCE indicator_type_seq RESTART WITH 8;

INSERT INTO source(id, code, name) VALUES (1, 'WB', 'World Bank');
INSERT INTO source(id, code, name) VALUES (2, 'acled', 'Armed Conflict Location and Event Dataset');
INSERT INTO source(id, code, name) VALUES (3, 'esa-unpd-WPP2012', 'esa-unpd-WPP2012');
INSERT INTO source(id, code, name) VALUES (4, 'emdat', 'emdat');
INSERT INTO source(id, code, name) VALUES (5, 'mdgs', 'mdgs');
INSERT INTO source(id, code, name) VALUES (6, 'faostat3', 'faostat3');

ALTER SEQUENCE source_seq RESTART WITH 7;
