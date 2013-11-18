INSERT INTO dap_user(id, ckanapikey, password) VALUES ('seustachi', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=');
 
INSERT INTO entity_type(id, code, name) VALUES (1, 'country', 'Country');
INSERT INTO entity_type(id, code, name) VALUES (2, 'crisis', 'Crisis');
 
INSERT INTO entity(id, code, name, entity_type_id) VALUES (3, 'RU', 'Russia', 1);
 
INSERT INTO indicator_type(id, code, name, unit) VALUES (4, 'capita-per-gdp', 'Capita per GDP', 'dollar');
INSERT INTO indicator_type(id, code, name) VALUES (5, 'population', 'Population');

INSERT INTO source(id, code, name) VALUES (6, 'WB', 'World Bank');

ALTER SEQUENCE hibernate_sequence RESTART WITH 7;
