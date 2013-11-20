INSERT INTO dap_user(id, ckanapikey, password) VALUES ('seustachi', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=');
 
INSERT INTO entity_type(id, code, name) VALUES (1, 'country', 'Country');
INSERT INTO entity_type(id, code, name) VALUES (2, 'crisis', 'Crisis');
 
INSERT INTO entity(id, code, name, entity_type_id) VALUES (3, 'RU', 'Russia', 1);
INSERT INTO entity(id, code, name, entity_type_id) VALUES (4, 'RWA', 'Rwanda', 1);
 
INSERT INTO indicator_type(id, code, name, unit) VALUES (5, 'capita-per-gdp', 'Capita per GDP', 'USD');
INSERT INTO indicator_type(id, code, name) VALUES (6, 'population', 'Population');
INSERT INTO indicator_type(id, code, name, unit) VALUES (7, 'PVX040', 'Incidence of conflict', 'Count');

INSERT INTO source(id, code, name) VALUES (8, 'WB', 'World Bank');
INSERT INTO source(id, code, name) VALUES (9, 'acled', 'Armed Conflict Location and Event Dataset');

ALTER SEQUENCE hibernate_sequence RESTART WITH 10;
