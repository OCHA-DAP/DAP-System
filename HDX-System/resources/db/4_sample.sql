/* hdx_user */
INSERT INTO hdx_user(id, ckanapikey, password, role) VALUES ('seustachi', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=', 'admin');
INSERT INTO hdx_user(id, ckanapikey, password, role) VALUES ('cjhendrix', 'G++lx4RQM5AqO3wlMiUhjZXU6ZtA3KKIq/cjjUznbTjgljHdUtJEnKm49sxcg3l1', '{SHA}zzsrXdRof78GkYbWwsfVZ2UbQmw=', 'api');
INSERT INTO hdx_user(id, ckanapikey, password, role) VALUES ('bmi', 'KHPUpxraF+oA1wkmG+s0hQ==', '{SHA}JuQIzlXAzjNi9ovRIG1IfdWw9k0=', 'admin');
/* END hdx_user */

/* entity_type moved to 3_countries.sql
INSERT INTO text(id, default_value) VALUES (28, 'Country');
INSERT INTO entity_type(id, code, text_id) VALUES (1, 'country', 28);

INSERT INTO text(id, default_value) VALUES (29, 'Crisis');
INSERT INTO entity_type(id, code, text_id) VALUES (2, 'crisis', 29);

ALTER SEQUENCE entity_type_seq RESTART WITH 3;
*/
/* END entity_type */

/* entity - countries - moved to 3_countries.sql
INSERT INTO text(id, default_value) VALUES (1, 'Russia');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (1, 'RUS', 1, 1);
INSERT INTO text(id, default_value) VALUES (2, 'Rwanda');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (2, 'RWA', 2, 1);
INSERT INTO text(id, default_value) VALUES (3, 'Cameroon');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (3, 'CMR', 3, 1);
INSERT INTO text(id, default_value) VALUES (4, 'Luxembourg');
INSERT INTO entity(id, code, text_id, entity_type_id) VALUES (4, 'LUX', 4, 1);

ALTER SEQUENCE entity_seq RESTART WITH 5;
*/
/*END  entity - countries*/

SELECT 'Starting language and translations' from text limit 0;

/* languages & translations */
INSERT INTO language(code, native_name) VALUES ('FR', 'Français');
INSERT INTO language(code, native_name) VALUES ('EN', 'English');

INSERT INTO hdx_translation(text, language, value) SELECT id, 'FR', 'Russie' FROM text WHERE default_value='RUSSIA' limit 1;
INSERT INTO hdx_translation(text, language, value) SELECT id, 'EN', 'Russia' FROM text WHERE default_value='RUSSIA' limit 1;
INSERT INTO hdx_translation(text, language, value) SELECT id, 'FR', 'Luxembourg' FROM text WHERE default_value='LUXEMBURG' limit 1;
INSERT INTO hdx_translation(text, language, value) SELECT id, 'EN', 'Luxemburg' FROM text WHERE default_value='LUXEMBURG' limit 1;
/* END languages & translations */

/* hdx_unit */
SELECT 'Starting hdx_unit' from text limit 1;
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), '% of population');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'%-of-population',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Percentage');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'percentage',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Number of people');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'number-of-people',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Current international $');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'current-international-$',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Number');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'number',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Per 100 inhabitants');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'per-100-inhabitants',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'sq. km');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'sq.-km',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), '2 Digit string');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'2-digit-string',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'current US$');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'current-us$',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Millions of people');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'millions-of-people',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Percentage of population');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'percentage-of-population',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'deaths per 1,000 population');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'deaths-per-1,000-population',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'url');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'url',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'string');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'string',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Date');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'date',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Number of disasters');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'number-of-disasters',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), ',000$ USD');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),',000$-usd',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), '%');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'%',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'per 100 people');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'per-100-people',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), '1=low to 5=high');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'1=low-to-5=high',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Constant 2005 international $');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'constant-2005-international-$',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), '% of working-age population');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'%-of-working-age-population',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'Index');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'index',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'thousands');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'thousands',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'years');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'years',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'persons per square km');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'persons-per-square-km',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'people per sq. km of land area');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'people-per-sq.-km-of-land-area',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'uno');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'uno',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), '% aged 15 and above');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'%-aged-15-and-above',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'both sexes');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'both-sexes',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'kcal/capita/day');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'kcal/capita/day',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'per 1,000 female adults');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'per-1,000-female-adults',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'per 1,000 male adults');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'per-1,000-male-adults',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'deaths under age five per 1,000 live births');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'deaths-under-age-five-per-1,000-live-births',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'per 1,000 live births');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'per-1,000-live-births',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'infant deaths per 1,000 live births');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'infant-deaths-per-1,000-live-births',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'deaths of women per100,000 live births');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'deaths-of-women-per100,000-live-births',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'ratio');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'ratio',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'count');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'count',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'km');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'km',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'total route-km');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'total-route-km',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'index value');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'index-value',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'incidents of conflict');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'incidents-of-conflict',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'per million people per year');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'per-million-people-per-year',currval('text_seq'));
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'), 'average per year per million people');
INSERT INTO hdx_unit(id, code, text_id) VALUES(nextval('hdx_unit_seq'),'average-per-year-per-million-people',currval('text_seq'));
/* END hdx_unit*/

/* indicator_type */
SELECT 'Starting indicator_type' from text limit 1;
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Percentage of population with access to electricity');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_Access to electricity (% of population)',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '%-of-population';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Percentage of children one year old immunized against measles');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_Children 1 year old immunized against measles, percentage',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'percentage';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Number of people made homeless by disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_emdat:no_homeless',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Number of people injured in disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_emdat:no_injured',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Total number of people affected by disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_emdat:total_affected',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Gross national income (GNI) converted to international dollars using purchasing power parity rates. ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_GNI, PPP (current international $)',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'current-international-$';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'GII: Gender Inequality Index, value');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_HDR:68606',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'number';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'International migrant stock ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_International migrant stock (% of population)',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '%-of-population';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Internet users per 100 inhabitants');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_Internet users per 100 inhabitants',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'per-100-inhabitants';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Land area ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_Land area (sq. km)',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'sq.-km';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Country code (m49-name)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_m49-name',currval('text_seq'),id,'STRING' from hdx_unit where code = '2-digit-string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Net ODA received per capita ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_Net ODA received per capita (current US$)',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'current-us$';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Number of infant deaths');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_Number of infant deaths',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Population undernourished (millions)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_Population undernourished, millions',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'millions-of-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Population undernourished (percentage)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_Population undernourished, percentage',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'percentage-of-population';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Population, total');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_Population, total',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Country code (ISO Country alpha-2-code)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_unterm:ISO Country alpha-2-code',currval('text_seq'),id,'STRING' from hdx_unit where code = '2-digit-string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Crude death rate by major area, region and country, 1950-2100');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'_WPP2012_MORT_F02_CRUDE_DEATH_RATE',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'deaths-per-1,000-population';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Wikipedia: geography');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CD010',currval('text_seq'),id,'STRING' from hdx_unit where code = 'url';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Wikipedia: climate');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CD030',currval('text_seq'),id,'STRING' from hdx_unit where code = 'url';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Wikipedia: economy');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CD050',currval('text_seq'),id,'STRING' from hdx_unit where code = 'url';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Wikipedia: transport');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CD060',currval('text_seq'),id,'STRING' from hdx_unit where code = 'url';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Wikipedia: education');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CD070',currval('text_seq'),id,'STRING' from hdx_unit where code = 'url';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Wikipedia: demographics');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CD080',currval('text_seq'),id,'STRING' from hdx_unit where code = 'url';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Wikipedia: religion');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CD090',currval('text_seq'),id,'STRING' from hdx_unit where code = 'url';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Formal Name');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG020',currval('text_seq'),id,'STRING' from hdx_unit where code = 'string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Short Name');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG030',currval('text_seq'),id,'STRING' from hdx_unit where code = 'string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'m49-num');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG060',currval('text_seq'),id,'STRING' from hdx_unit where code = 'string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'ISO Country alpha-3-code');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG070',currval('text_seq'),id,'STRING' from hdx_unit where code = 'string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Capital City');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG080',currval('text_seq'),id,'STRING' from hdx_unit where code = 'string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Languages');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG100',currval('text_seq'),id,'STRING' from hdx_unit where code = 'string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Currency Abbr.');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG120',currval('text_seq'),id,'STRING' from hdx_unit where code = 'string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'ISO Currency Code');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG140',currval('text_seq'),id,'STRING' from hdx_unit where code = 'string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Income Category');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG150',currval('text_seq'),id,'STRING' from hdx_unit where code = 'string';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'AccuWeather URL');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG260',currval('text_seq'),id,'STRING' from hdx_unit where code = 'url';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Date of Entry into UN');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CG290',currval('text_seq'),id,'DATE' from hdx_unit where code = 'date';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Number of disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CH070',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'number-of-disasters';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'People killed in disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CH080',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'People affected by disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CH090',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Total cost of damage done by disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'CH100',currval('text_seq'),id,'NUMBER' from hdx_unit where code = ',000$-usd';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Routine EPI vaccines financed by government');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PCH090',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '%';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Mobile cellular subscriptions per 100 inhabitants');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PCX051',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'per-100-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Fixed telephone lines per 100 inhabitants');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PCX080',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'per-100-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Mobile cellular subscriptions ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PCX090',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'per-100-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Internet users ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PCX100',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'per-100-people';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Logistics performance index: Overall ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PCX130',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '1=low-to-5=high';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'GDP per capita, PPP ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE030',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'current-international-$';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'GNI per capita, PPP ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE090',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'current-international-$';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'GNI per capita in PPP terms (constant 2005 international $)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE110',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'constant-2005-international-$';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'GNI (current US$)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE120',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'current-us$';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Household final consumption expenditure, PPP ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE130',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'constant-2005-international-$';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Age dependency ratio, old ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE140',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '%-of-working-age-population';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Age dependency ratio, young ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE150',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '%-of-working-age-population';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'MPI: Population living below $1.25 PPP per day');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE160',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '%';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Poverty headcount ratio at $1.25 a day (PPP) ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE170',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '%-of-population';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Inflation, consumer prices ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE200',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'percentage';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'GINI index');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE210',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'index';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Human Development Index rank');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSE220',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'index';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Total population (both sexes combined) by major area, region and country, annually for 1950-2010');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSP010',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'thousands';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Average annual rate of population change by major area, region and country, 1950-2010');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSP050',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'percentage';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Population growth ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSP060',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'percentage';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Median age by major area, region and country, 1950-2010');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSP070',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'years';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Population density by major area, region and country, 1950-2010');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSP080',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'persons-per-square-km';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Population density ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSP090',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'people-per-sq.-km-of-land-area';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Percentage of Population Residing in Urban Areas by Major Area, Region and Country, 1950-2050');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSP100',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'percentage';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Population in urban agglomerations of more than 1 million');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PSP110',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'uno';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Public expenditure on education (% of GDP)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVE010',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '%';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Expected Years of Schooling (of children)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVE030',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'years';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Adult literacy rate, both sexes');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVE040',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '%-aged-15-and-above';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Mean years of schooling (of adults)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVE110',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'years';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Combined gross enrolment in education (both sexes)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVE120',currval('text_seq'),id,'NUMBER' from hdx_unit where code = '%';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Total net enrolment ratio in primary education');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVE130',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'both-sexes';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Per capita food supply');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVF020',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'kcal/capita/day';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Life expectancy at birth (both sexes combined) by major area, region and country, 1950-2010');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVH010',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'years';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Deaths (both sexes combined) by major area, region and country, 1950-2010');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVH050',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'thousands';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Mortality rate, adult, female ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVH080',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'per-1,000-female-adults';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Mortality rate, adult, male ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVH090',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'per-1,000-male-adults';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Under-five mortality (both sexes combined) by major area, region and country, 1950-2010');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVH100',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'deaths-under-age-five-per-1,000-live-births';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Under-five mortality');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVH120',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'per-1,000-live-births';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Children under five mortality rate per 1,000 live births');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVH140',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'per-1,000-live-births';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Infant mortality rate (both sexes combined) by major area, region and country, 1950-2100');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVH150',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'infant-deaths-per-1,000-live-births';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Maternal mortality ratio');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVH180',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'deaths-of-women-per100,000-live-births';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Maternal mortality ratio per 100,000 live births');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVH190',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'ratio';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Number of airports');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVL010',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'count';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Roads, total network ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVL030',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'km';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Rail lines ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVL040',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'total-route-km';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Prevalence of undernourishment');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVN010',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'percentage';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Children under 5 moderately or severely underweight');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVN050',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'percentage';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Proportion of the population using improved drinking water sources');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVW010',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'percentage';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Proportion of the population using improved sanitation facilities');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVW040',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'percentage';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'GNA Vulnerability Index');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVX010',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'index-value';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'GNA Crisis Index');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVX020',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'index-value';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Incidence of Conflict');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVX040',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'incidents-of-conflict';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Impact of natural disasters: number of deaths');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVX060',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'per-million-people-per-year';
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Impact of natural disasters: population affected (average per year/million)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT nextval('indicator_type_seq'),'PVX070',currval('text_seq'),id,'NUMBER' from hdx_unit where code = 'average-per-year-per-million-people';

/* END indicatory_type */

/* organisations */
SELECT 'Starting organizations' from text limit 1;
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Accuweather');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'ACCW');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://www.accuweather.com/', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Armed Conflict Location & Event Dataset');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'ACLED');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://www.acleddata.com', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Centre for Research on the Epidemiology of Disasters');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'CRED');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://www.emdat.be/database', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'European Commission');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'EC');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://ec.europa.eu', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Food and Agricultural organization of the United Nations');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'FAO');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://faostat3.fao.org/faostat-gateway/go/to/browse/C/*/E', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'UN Department of Economic and Social Affairs Population Division');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'Population Division');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://www.un.org/en/development/desa/population/', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Relief web');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'RW');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://reliefweb.int/', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'DESA United Nations Indicators for Monitoring the Millennium Development Goals');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'UNDESA MDGS');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://mdgs.un.org/unsd/mdg/Default.aspx', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'UNDP Human Development Reports');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'UNDP HDR');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://hdr.undp.org/en/data/explorer', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Unicef Statistics and Monitoring');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'Unicef');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://www.unicef.org/statistics/', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'United Nations Multilingual Terminology Database');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'UNTERM');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://unterm.un.org/', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Wikipedia');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'WIKI');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://www.wikipedia.org/', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'World Bank');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'WB');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://www.worldbank.org/', currval('text_seq')-1, currval('text_seq');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'),'Worldaerodata');
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'WAD');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) SELECT nextval('organisation_seq'),'http://worldaerodata.com/', currval('text_seq')-1, currval('text_seq');
/* END organisations */

/* source 
INSERT INTO text(id, default_value) VALUES (22, 'World Bank');
INSERT INTO source(id, code, text_id, organisation_id) VALUES (1, 'WB', 22, 1);
INSERT INTO text(id, default_value) VALUES (23, 'Armed Conflict Location and Event Dataset');
INSERT INTO source(id, code, text_id) VALUES (2, 'acled', 23);
INSERT INTO text(id, default_value) VALUES (24, 'esa-unpd-WPP2012');
INSERT INTO source(id, code, text_id, organisation_id) VALUES (3, 'esa-unpd-WPP2012', 24, 2);
INSERT INTO text(id, default_value) VALUES (25, 'emdat');
INSERT INTO source(id, code, text_id,organisation_id) VALUES (4, 'emdat', 25, 3);
INSERT INTO text(id, default_value) VALUES (26, 'mdgs');
INSERT INTO source(id, code, text_id, organisation_id) VALUES (5, 'mdgs', 26, 4);
INSERT INTO text(id, default_value) VALUES (27, 'faostat3');
INSERT INTO source(id, code, text_id, organisation_id) VALUES (6, 'faostat3', 27, 5);
ALTER SEQUENCE source_seq RESTART WITH 7;

*/
/* sources */
SELECT 'Starting sources' from text limit 0;
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'World Bank');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'world-bank', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'mdgs');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'mdgs', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'emdat');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'emdat', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'HDRStats');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'hdrstats', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'm49');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'm49', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'unterm');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'unterm', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'esa-unpd-WPP2012');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'esa-unpd-wpp2012', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'wikipedia');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'wikipedia', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'worldbank-lending-groups');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'worldbank-lending-groups', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'accuweather');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'accuweather', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'unicef-infobycountry');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'unicef-infobycountry', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'esa-unpd-WUP2011');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'esa-unpd-wup2011', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'faostat3');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'faostat3', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'worldaerodata');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'worldaerodata', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'fao-foodsec');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'fao-foodsec', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'echo');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'echo', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'acled');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'acled', currval('text_seq'));
INSERT INTO text(id, default_value) VALUES(nextval('text_seq'), 'hdi-disaster');
INSERT INTO source(id, code, text_id) VALUES(nextval('source_seq'), 'hdi-disaster', currval('text_seq'));
/* END sources */

/* restart seqs 
ALTER SEQUENCE hdx_unit_seq RESTART WITH 46;
ALTER SEQUENCE indicator_type_seq RESTART WITH 96;
ALTER SEQUENCE organisation_seq RESTART WITH 15;
ALTER SEQUENCE text_seq RESTART WITH 430;
ALTER SEQUENCE source_seq RESTART WITH 19;
*/

update indicator_type set value_type = upper(value_type);
/* other sample date */

/* samples for xls overview */
/*
INSERT INTO hdx_indicator(
            id, end_time, initial_value, periodicity, source_link, start_time, 
            string_value, entity_id, 
            import_from_ckan_id, source_id, type_id)
    VALUES (1, '2011-01-01', 'Url for USA', 'YEAR', 'Wikipedia: geography', '2010-01-01', 
               'Url for USA', 1228, 1, 1, 8);


ALTER SEQUENCE indicator_type_seq RESTART WITH 9;
ALTER SEQUENCE text_seq RESTART WITH 39;
ALTER SEQUENCE hdx_unit_seq RESTART WITH 8;
*/