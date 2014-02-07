INSERT INTO hdx_user(id, ckanapikey, password, role) VALUES ('seustachi', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=', 'admin');
INSERT INTO hdx_user(id, ckanapikey, password, role) VALUES ('cjhendrix', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=', 'api');
INSERT INTO hdx_user(id, ckanapikey, password, role) VALUES ('bmi', 'KHPUpxraF+oA1wkmG+s0hQ==', '{SHA}JuQIzlXAzjNi9ovRIG1IfdWw9k0=', 'admin');

INSERT INTO text(id, default_value) VALUES (18, 'Country');
INSERT INTO entity_type(id, code, text_id) VALUES (1, 'country', 18);

INSERT INTO text(id, default_value) VALUES (19, 'Crisis');
INSERT INTO entity_type(id, code, text_id) VALUES (2, 'crisis', 19);

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
INSERT INTO source(id, code, text_id) VALUES (1, 'WB', 12);
INSERT INTO text(id, default_value) VALUES (13, 'Armed Conflict Location and Event Dataset');
INSERT INTO source(id, code, text_id) VALUES (2, 'acled', 13);
INSERT INTO text(id, default_value) VALUES (14, 'esa-unpd-WPP2012');
INSERT INTO source(id, code, text_id) VALUES (3, 'esa-unpd-WPP2012', 14);
INSERT INTO text(id, default_value) VALUES (15, 'emdat');
INSERT INTO source(id, code, text_id) VALUES (4, 'emdat', 15);
INSERT INTO text(id, default_value) VALUES (16, 'mdgs');
INSERT INTO source(id, code, text_id) VALUES (5, 'mdgs', 16);
INSERT INTO text(id, default_value) VALUES (17, 'faostat3');
INSERT INTO source(id, code, text_id) VALUES (6, 'faostat3', 17);

ALTER SEQUENCE source_seq RESTART WITH 7;
ALTER SEQUENCE text_seq RESTART WITH 20;
