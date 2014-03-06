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

/* languages & translations */
INSERT INTO language(code, native_name) VALUES ('FR', 'Français');
INSERT INTO language(code, native_name) VALUES ('EN', 'English');

INSERT INTO hdx_translation(text, language, value) VALUES (181, 'FR', 'Russie');
INSERT INTO hdx_translation(text, language, value) VALUES (181, 'EN', 'Russia');
INSERT INTO hdx_translation(text, language, value) VALUES (128, 'FR', 'Luxembourg');
INSERT INTO hdx_translation(text, language, value) VALUES (128, 'EN', 'Luxemburg');

/* END languages & translations */

/* hdx_unit */
INSERT INTO text(id,default_value) VALUES(244,'% of population');
INSERT INTO hdx_unit(id, code, text_id) VALUES(1,'%-of-population',244);
INSERT INTO text(id,default_value) VALUES(245,'Percentage');
INSERT INTO hdx_unit(id, code, text_id) VALUES(2,'percentage',245);
INSERT INTO text(id,default_value) VALUES(246,'Number of people');
INSERT INTO hdx_unit(id, code, text_id) VALUES(3,'number-of-people',246);
INSERT INTO text(id,default_value) VALUES(247,'Current international $');
INSERT INTO hdx_unit(id, code, text_id) VALUES(4,'current-international-$',247);
INSERT INTO text(id,default_value) VALUES(248,'Number');
INSERT INTO hdx_unit(id, code, text_id) VALUES(5,'number',248);
INSERT INTO text(id,default_value) VALUES(249,'Per 100 inhabitants');
INSERT INTO hdx_unit(id, code, text_id) VALUES(6,'per-100-inhabitants',249);
INSERT INTO text(id,default_value) VALUES(250,'sq. km');
INSERT INTO hdx_unit(id, code, text_id) VALUES(7,'sq.-km',250);
INSERT INTO text(id,default_value) VALUES(251,'2 Digit string');
INSERT INTO hdx_unit(id, code, text_id) VALUES(8,'2-digit-string',251);
INSERT INTO text(id,default_value) VALUES(252,'current US$');
INSERT INTO hdx_unit(id, code, text_id) VALUES(9,'current-us$',252);
INSERT INTO text(id,default_value) VALUES(253,'Millions of people');
INSERT INTO hdx_unit(id, code, text_id) VALUES(10,'millions-of-people',253);
INSERT INTO text(id,default_value) VALUES(254,'Percentage of population');
INSERT INTO hdx_unit(id, code, text_id) VALUES(11,'percentage-of-population',254);
INSERT INTO text(id,default_value) VALUES(255,'deaths per 1,000 population');
INSERT INTO hdx_unit(id, code, text_id) VALUES(12,'deaths-per-1,000-population',255);
INSERT INTO text(id,default_value) VALUES(256,'url');
INSERT INTO hdx_unit(id, code, text_id) VALUES(13,'url',256);
INSERT INTO text(id,default_value) VALUES(257,'string');
INSERT INTO hdx_unit(id, code, text_id) VALUES(14,'string',257);
INSERT INTO text(id,default_value) VALUES(258,'Date');
INSERT INTO hdx_unit(id, code, text_id) VALUES(15,'date',258);
INSERT INTO text(id,default_value) VALUES(259,'Number of disasters');
INSERT INTO hdx_unit(id, code, text_id) VALUES(16,'number-of-disasters',259);
INSERT INTO text(id,default_value) VALUES(260,',000$ USD');
INSERT INTO hdx_unit(id, code, text_id) VALUES(17,',000$-usd',260);
INSERT INTO text(id,default_value) VALUES(261,'%');
INSERT INTO hdx_unit(id, code, text_id) VALUES(18,'%',261);
INSERT INTO text(id,default_value) VALUES(262,'per 100 people');
INSERT INTO hdx_unit(id, code, text_id) VALUES(19,'per-100-people',262);
INSERT INTO text(id,default_value) VALUES(263,'1=low to 5=high');
INSERT INTO hdx_unit(id, code, text_id) VALUES(20,'1=low-to-5=high',263);
INSERT INTO text(id,default_value) VALUES(264,'Constant 2005 international $');
INSERT INTO hdx_unit(id, code, text_id) VALUES(21,'constant-2005-international-$',264);
INSERT INTO text(id,default_value) VALUES(265,'% of working-age population');
INSERT INTO hdx_unit(id, code, text_id) VALUES(22,'%-of-working-age-population',265);
INSERT INTO text(id,default_value) VALUES(266,'annual %');
INSERT INTO hdx_unit(id, code, text_id) VALUES(23,'annual-%',266);
INSERT INTO text(id,default_value) VALUES(267,'uno');
INSERT INTO hdx_unit(id, code, text_id) VALUES(24,'uno',267);
INSERT INTO text(id,default_value) VALUES(268,'thousands');
INSERT INTO hdx_unit(id, code, text_id) VALUES(25,'thousands',268);
INSERT INTO text(id,default_value) VALUES(269,'years');
INSERT INTO hdx_unit(id, code, text_id) VALUES(26,'years',269);
INSERT INTO text(id,default_value) VALUES(270,'persons per square km');
INSERT INTO hdx_unit(id, code, text_id) VALUES(27,'persons-per-square-km',270);
INSERT INTO text(id,default_value) VALUES(271,'people per sq. km of land area');
INSERT INTO hdx_unit(id, code, text_id) VALUES(28,'people-per-sq.-km-of-land-area',271);
INSERT INTO text(id,default_value) VALUES(272,'% aged 15 and above');
INSERT INTO hdx_unit(id, code, text_id) VALUES(29,'%-aged-15-and-above',272);
INSERT INTO text(id,default_value) VALUES(273,'both sexes');
INSERT INTO hdx_unit(id, code, text_id) VALUES(30,'both-sexes',273);
INSERT INTO text(id,default_value) VALUES(274,'kcal/capita/day');
INSERT INTO hdx_unit(id, code, text_id) VALUES(31,'kcal/capita/day',274);
INSERT INTO text(id,default_value) VALUES(275,'per 1,000 female adults');
INSERT INTO hdx_unit(id, code, text_id) VALUES(32,'per-1,000-female-adults',275);
INSERT INTO text(id,default_value) VALUES(276,'per 1,000 male adults');
INSERT INTO hdx_unit(id, code, text_id) VALUES(33,'per-1,000-male-adults',276);
INSERT INTO text(id,default_value) VALUES(277,'deaths under age five per 1,000 live births');
INSERT INTO hdx_unit(id, code, text_id) VALUES(34,'deaths-under-age-five-per-1,000-live-births',277);
INSERT INTO text(id,default_value) VALUES(278,'per 1,000 live births');
INSERT INTO hdx_unit(id, code, text_id) VALUES(35,'per-1,000-live-births',278);
INSERT INTO text(id,default_value) VALUES(279,'infant deaths per 1,000 live births');
INSERT INTO hdx_unit(id, code, text_id) VALUES(36,'infant-deaths-per-1,000-live-births',279);
INSERT INTO text(id,default_value) VALUES(280,'deaths of women per100,000 live births');
INSERT INTO hdx_unit(id, code, text_id) VALUES(37,'deaths-of-women-per100,000-live-births',280);
INSERT INTO text(id,default_value) VALUES(281,'ratio');
INSERT INTO hdx_unit(id, code, text_id) VALUES(38,'ratio',281);
INSERT INTO text(id,default_value) VALUES(282,'count');
INSERT INTO hdx_unit(id, code, text_id) VALUES(39,'count',282);
INSERT INTO text(id,default_value) VALUES(283,'km');
INSERT INTO hdx_unit(id, code, text_id) VALUES(40,'km',283);
INSERT INTO text(id,default_value) VALUES(284,'total route-km');
INSERT INTO hdx_unit(id, code, text_id) VALUES(41,'total-route-km',284);
INSERT INTO text(id,default_value) VALUES(285,'index value');
INSERT INTO hdx_unit(id, code, text_id) VALUES(42,'index-value',285);
INSERT INTO text(id,default_value) VALUES(286,'incidents of conflict');
INSERT INTO hdx_unit(id, code, text_id) VALUES(43,'incidents-of-conflict',286);
INSERT INTO text(id,default_value) VALUES(287,'per million people per year');
INSERT INTO hdx_unit(id, code, text_id) VALUES(44,'per-million-people-per-year',287);
INSERT INTO text(id,default_value) VALUES(288,'average per year per million people');
INSERT INTO hdx_unit(id, code, text_id) VALUES(45,'average-per-year-per-million-people',288);
/* END hdx_unit*/

/* indicatory_type */
/* old import
INSERT INTO text(id, default_value) VALUES (5, 'Incidence of conflict');
INSERT INTO text(id, default_value) VALUES (30, 'Count');
INSERT INTO hdx_unit(id, code, text_id) VALUES (1, 'count', 30);
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) VALUES (1, 'PVX040', 5, 1, 'NUMBER');

INSERT INTO text(id, default_value) VALUES (6, 'Population Density');
INSERT INTO text(id, default_value) VALUES (31, 'persons per square km');
INSERT INTO hdx_unit(id, code, text_id) VALUES (2, 'persons-per-sq-km', 31);
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) VALUES (2, 'PSP080', 6, 2, 'NUMBER');

INSERT INTO text(id, default_value) VALUES (7, 'Population (Total M+F)');
INSERT INTO text(id, default_value) VALUES (32, 'persons');
INSERT INTO hdx_unit(id, code, text_id) VALUES (3, 'persons', 32);
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) VALUES (3, 'PSP010', 7, 3, 'NUMBER');

INSERT INTO text(id, default_value) VALUES (8, 'GDP per capita, PPP');
INSERT INTO text(id, default_value) VALUES (33, 'current international $');
INSERT INTO hdx_unit(id, code, text_id) VALUES (4, 'current-international-$', 33);
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) VALUES (4, 'PSE030', 8, 4, 'NUMBER');

INSERT INTO text(id, default_value) VALUES (9, 'Total affected');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) VALUES (5, '_emdat:total_affected', 9, 3, 'NUMBER');

INSERT INTO text(id, default_value) VALUES (10, 'Mobile cellular subscriptions');
INSERT INTO text(id, default_value) VALUES (34, 'per 100 inhabitants');
INSERT INTO hdx_unit(id, code, text_id) VALUES (5, 'per-100-inhabitants', 34);
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) VALUES (6, 'PCX051', 10, 5, 'NUMBER');

INSERT INTO text(id, default_value) VALUES (11, 'Per capita food supply');
INSERT INTO text(id, default_value) VALUES (35, 'kcal/capita/day');
INSERT INTO hdx_unit(id, code, text_id) VALUES (6, 'kcal/capita/day', 35);
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) VALUES (7, 'PVF020', 11, 6, 'NUMBER');

ALTER SEQUENCE indicator_type_seq RESTART WITH 8;
ALTER SEQUENCE hdx_unit_seq RESTART WITH 7;
*/
/* START new indicator_type */
INSERT INTO text(id,default_value) VALUES(289,'Percentage of population with access to electricity');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 1,'_Access to electricity (% of population)',289,id,'Number' from hdx_unit where code = '%-of-population';
INSERT INTO text(id,default_value) VALUES(290,'Percentage of children one year old immunized against measles');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 2,'_Children 1 year old immunized against measles, percentage',290,id,'Number' from hdx_unit where code = 'percentage';
INSERT INTO text(id,default_value) VALUES(291,'Number of people made homeless by disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 3,'_emdat:no_homeless',291,id,'Number' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id,default_value) VALUES(292,'Number of people injured in disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 4,'_emdat:no_injured',292,id,'Number' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id,default_value) VALUES(293,'Total number of people affected by disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 5,'_emdat:total_affected',293,id,'Number' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id,default_value) VALUES(294,'Gross national income (GNI) converted to international dollars using purchasing power parity rates. ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 6,'_GNI, PPP (current international $)',294,id,'Number' from hdx_unit where code = 'current-international-$';
INSERT INTO text(id,default_value) VALUES(295,'GII: Gender Inequality Index, value');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 7,'_HDR:68606',295,id,'Number' from hdx_unit where code = 'number';
INSERT INTO text(id,default_value) VALUES(296,'International migrant stock ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 8,'_International migrant stock (% of population)',296,id,'Number' from hdx_unit where code = '%-of-population';
INSERT INTO text(id,default_value) VALUES(297,'Internet users per 100 inhabitants');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 9,'_Internet users per 100 inhabitants',297,id,'Number' from hdx_unit where code = 'per-100-inhabitants';
INSERT INTO text(id,default_value) VALUES(298,'Land area ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 10,'_Land area (sq. km)',298,id,'Number' from hdx_unit where code = 'sq.-km';
INSERT INTO text(id,default_value) VALUES(299,'Country code (m49-name)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 11,'_m49-name',299,id,'Number' from hdx_unit where code = '2-digit-string';
INSERT INTO text(id,default_value) VALUES(300,'Net ODA received per capita ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 12,'_Net ODA received per capita (current US$)',300,id,'Number' from hdx_unit where code = 'current-us$';
INSERT INTO text(id,default_value) VALUES(301,'Number of infant deaths');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 13,'_Number of infant deaths',301,id,'Number' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id,default_value) VALUES(302,'Population undernourished (millions)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 14,'_Population undernourished, millions',302,id,'Number' from hdx_unit where code = 'millions-of-people';
INSERT INTO text(id,default_value) VALUES(303,'Population undernourished (percentage)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 15,'_Population undernourished, percentage',303,id,'Number' from hdx_unit where code = 'percentage-of-population';
INSERT INTO text(id,default_value) VALUES(304,'Population, total');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 16,'_Population, total',304,id,'Number' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id,default_value) VALUES(305,'Country code (ISO Country alpha-2-code)');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 17,'_unterm:ISO Country alpha-2-code',305,id,'String' from hdx_unit where code = '2-digit-string';
INSERT INTO text(id,default_value) VALUES(306,'Crude death rate by major area, region and country, 1950-2100');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 18,'_WPP2012_MORT_F02_CRUDE_DEATH_RATE',306,id,'Number' from hdx_unit where code = 'deaths-per-1,000-population';
INSERT INTO text(id,default_value) VALUES(307,'Wikipedia: geography');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 19,'CD010',307,id,'String' from hdx_unit where code = 'url';
INSERT INTO text(id,default_value) VALUES(308,'Wikipedia: climate');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 20,'CD030',308,id,'String' from hdx_unit where code = 'url';
INSERT INTO text(id,default_value) VALUES(309,'Wikipedia: economy');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 21,'CD050',309,id,'String' from hdx_unit where code = 'url';
INSERT INTO text(id,default_value) VALUES(310,'Wikipedia: transport');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 22,'CD060',310,id,'String' from hdx_unit where code = 'url';
INSERT INTO text(id,default_value) VALUES(311,'Wikipedia: education');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 23,'CD070',311,id,'String' from hdx_unit where code = 'url';
INSERT INTO text(id,default_value) VALUES(312,'Wikipedia: demographics');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 24,'CD080',312,id,'String' from hdx_unit where code = 'url';
INSERT INTO text(id,default_value) VALUES(313,'Wikipedia: religion');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 25,'CD090',313,id,'String' from hdx_unit where code = 'url';
INSERT INTO text(id,default_value) VALUES(314,'Formal Name');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 26,'CG020',314,id,'String' from hdx_unit where code = 'string';
INSERT INTO text(id,default_value) VALUES(315,'Short Name');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 27,'CG030',315,id,'String' from hdx_unit where code = 'string';
INSERT INTO text(id,default_value) VALUES(316,'m49-num');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 28,'CG060',316,id,'String' from hdx_unit where code = 'string';
INSERT INTO text(id,default_value) VALUES(317,'ISO Country alpha-3-code');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 29,'CG070',317,id,'String' from hdx_unit where code = 'string';
INSERT INTO text(id,default_value) VALUES(318,'Capital City');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 30,'CG080',318,id,'String' from hdx_unit where code = 'string';
INSERT INTO text(id,default_value) VALUES(319,'Languages');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 31,'CG100',319,id,'String' from hdx_unit where code = 'string';
INSERT INTO text(id,default_value) VALUES(320,'Currency Abbr.');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 32,'CG120',320,id,'String' from hdx_unit where code = 'string';
INSERT INTO text(id,default_value) VALUES(321,'ISO Currency Code');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 33,'CG140',321,id,'String' from hdx_unit where code = 'string';
INSERT INTO text(id,default_value) VALUES(322,'Income Category');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 34,'CG150',322,id,'String' from hdx_unit where code = 'string';
INSERT INTO text(id,default_value) VALUES(323,'AccuWeather URL');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 35,'CG260',323,id,'String' from hdx_unit where code = 'url';
INSERT INTO text(id,default_value) VALUES(324,'Date of Entry into UN');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 36,'CG290',324,id,'Date' from hdx_unit where code = 'date';
INSERT INTO text(id,default_value) VALUES(325,'Number of disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 37,'CH070',325,id,'Number' from hdx_unit where code = 'number-of-disasters';
INSERT INTO text(id,default_value) VALUES(326,'People killed in disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 38,'CH080',326,id,'Number' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id,default_value) VALUES(327,'People affected by disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 39,'CH090',327,id,'Number' from hdx_unit where code = 'number-of-people';
INSERT INTO text(id,default_value) VALUES(328,'Total cost of damage done by disasters');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 40,'CH100',328,id,'Number' from hdx_unit where code = ',000$-usd';
INSERT INTO text(id,default_value) VALUES(329,'Routine EPI vaccines financed by government');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 41,'PCH090',329,id,'Number' from hdx_unit where code = '%';
INSERT INTO text(id,default_value) VALUES(330,'Mobile cellular subscriptions per 100 inhabitants');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 42,'PCX051',330,id,'Number' from hdx_unit where code = 'per-100-people';
INSERT INTO text(id,default_value) VALUES(331,'Fixed telephone lines per 100 inhabitants');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 43,'PCX080',331,id,'Number' from hdx_unit where code = 'per-100-people';
INSERT INTO text(id,default_value) VALUES(332,'Mobile cellular subscriptions ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 44,'PCX090',332,id,'Number' from hdx_unit where code = 'per-100-people';
INSERT INTO text(id,default_value) VALUES(333,'Internet users ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 45,'PCX100',333,id,'Number' from hdx_unit where code = 'per-100-people';
INSERT INTO text(id,default_value) VALUES(334,'Logistics performance index: Overall ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 46,'PCX130',334,id,'Number' from hdx_unit where code = '1=low-to-5=high';
INSERT INTO text(id,default_value) VALUES(335,'GDP per capita, PPP ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 47,'PSE030',335,id,'Number' from hdx_unit where code = 'current-international-$';
INSERT INTO text(id,default_value) VALUES(336,'GNI per capita, PPP ');
INSERT INTO indicator_type(id, code, text_id, unit_id, value_type) SELECT 48,'PSE090',336,id,'Number' from hdx_unit where code = 'current-international-$';
/* END indicatory_type */

/* organisations */
INSERT INTO text(id, default_value) VALUES(337, 'Accuweather');
INSERT INTO text(id, default_value) VALUES(338, 'ACCW');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(1,'http://www.accuweather.com/',337,338);
INSERT INTO text(id, default_value) VALUES(339, 'Armed Conflict Location & Event Dataset');
INSERT INTO text(id, default_value) VALUES(340, 'ACLED');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(2,'http://www.acleddata.com',339,340);
INSERT INTO text(id, default_value) VALUES(341, 'Centre for Research on the Epidemiology of Disasters');
INSERT INTO text(id, default_value) VALUES(342, 'CRED');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(3,'http://www.emdat.be/database',341,342);
INSERT INTO text(id, default_value) VALUES(343, 'European Commission');
INSERT INTO text(id, default_value) VALUES(344, 'EC');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(4,'http://ec.europa.eu',343,344);
INSERT INTO text(id, default_value) VALUES(345, 'Food and Agricultural organization of the United Nations');
INSERT INTO text(id, default_value) VALUES(346, 'FAO');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(5,'http://faostat3.fao.org/faostat-gateway/go/to/browse/C/*/E',345,346);
INSERT INTO text(id, default_value) VALUES(347, 'UN Department of Economic and Social Affairs Population Division');
INSERT INTO text(id, default_value) VALUES(348, 'Population Division');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(6,'http://www.un.org/en/development/desa/population/',347,348);
INSERT INTO text(id, default_value) VALUES(349, 'Relief web');
INSERT INTO text(id, default_value) VALUES(350, 'RW');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(7,'http://reliefweb.int/',349,350);
INSERT INTO text(id, default_value) VALUES(351, 'DESA United Nations Indicators for Monitoring the Millennium Development Goals');
INSERT INTO text(id, default_value) VALUES(352, 'UNDESA MDGS');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(8,'http://mdgs.un.org/unsd/mdg/Default.aspx',351,352);
INSERT INTO text(id, default_value) VALUES(353, 'UNDP Human Development Reports');
INSERT INTO text(id, default_value) VALUES(354, 'UNDP HDR');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(9,'http://hdr.undp.org/en/data/explorer',353,354);
INSERT INTO text(id, default_value) VALUES(355, 'Unicef Statistics and Monitoring');
INSERT INTO text(id, default_value) VALUES(356, 'Unicef');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(10,'http://www.unicef.org/statistics/',355,356);
INSERT INTO text(id, default_value) VALUES(357, 'United Nations Multilingual Terminology Database');
INSERT INTO text(id, default_value) VALUES(358, 'UNTERM');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(11,'http://unterm.un.org/',357,358);
INSERT INTO text(id, default_value) VALUES(359, 'Wikipedia');
INSERT INTO text(id, default_value) VALUES(360, 'WIKI');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(12,'http://www.wikipedia.org/',359,360);
INSERT INTO text(id, default_value) VALUES(361, 'World Bank');
INSERT INTO text(id, default_value) VALUES(362, 'WB');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(13,'http://www.worldbank.org/',361,362);
INSERT INTO text(id, default_value) VALUES(363, 'Worldaerodata');
INSERT INTO text(id, default_value) VALUES(364, 'WAD');
INSERT INTO organisation(id, org_link, full_name_id, short_name_id) VALUES(14,'http://worldaerodata.com/',363,364);/* END organisations */

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
INSERT INTO text(id,default_value) VALUES(365,'World Bank');
INSERT INTO source(id, code, text_id) VALUES(1,'world-bank',365);
INSERT INTO text(id,default_value) VALUES(366,'mdgs');
INSERT INTO source(id, code, text_id) VALUES(2,'mdgs',366);
INSERT INTO text(id,default_value) VALUES(367,'emdat');
INSERT INTO source(id, code, text_id) VALUES(3,'emdat',367);
INSERT INTO text(id,default_value) VALUES(368,'HDRStats');
INSERT INTO source(id, code, text_id) VALUES(4,'hdrstats',368);
INSERT INTO text(id,default_value) VALUES(369,'String');
INSERT INTO source(id, code, text_id) VALUES(5,'string',369);
INSERT INTO text(id,default_value) VALUES(370,'esa-unpd-WPP2012');
INSERT INTO source(id, code, text_id) VALUES(6,'esa-unpd-wpp2012',370);
INSERT INTO text(id,default_value) VALUES(371,'wikipedia');
INSERT INTO source(id, code, text_id) VALUES(7,'wikipedia',371);
INSERT INTO text(id,default_value) VALUES(372,'unterm');
INSERT INTO source(id, code, text_id) VALUES(8,'unterm',372);
INSERT INTO text(id,default_value) VALUES(373,'worldbank-lending-groups');
INSERT INTO source(id, code, text_id) VALUES(9,'worldbank-lending-groups',373);
INSERT INTO text(id,default_value) VALUES(374,'accuweather');
INSERT INTO source(id, code, text_id) VALUES(10,'accuweather',374);
INSERT INTO text(id,default_value) VALUES(375,'unicef-infobycountry');
INSERT INTO source(id, code, text_id) VALUES(11,'unicef-infobycountry',375);
INSERT INTO text(id,default_value) VALUES(376,'esa-unpd-WUP2011');
INSERT INTO source(id, code, text_id) VALUES(12,'esa-unpd-wup2011',376);
INSERT INTO text(id,default_value) VALUES(377,'faostat3');
INSERT INTO source(id, code, text_id) VALUES(13,'faostat3',377);
INSERT INTO text(id,default_value) VALUES(378,'worldaerodata');
INSERT INTO source(id, code, text_id) VALUES(14,'worldaerodata',378);
INSERT INTO text(id,default_value) VALUES(379,'fao-foodsec');
INSERT INTO source(id, code, text_id) VALUES(15,'fao-foodsec',379);
INSERT INTO text(id,default_value) VALUES(380,'echo');
INSERT INTO source(id, code, text_id) VALUES(16,'echo',380);
INSERT INTO text(id,default_value) VALUES(381,'acled');
INSERT INTO source(id, code, text_id) VALUES(17,'acled',381);
INSERT INTO text(id,default_value) VALUES(382,'hdi-disaster');
INSERT INTO source(id, code, text_id) VALUES(18,'hdi-disaster',382);
/* END sources */

/* restart seqs */
ALTER SEQUENCE hdx_unit_seq RESTART WITH 46;
ALTER SEQUENCE indicator_type_seq RESTART WITH 49;
ALTER SEQUENCE organisation_seq RESTART WITH 15;
ALTER SEQUENCE text_seq RESTART WITH 383;
ALTER SEQUENCE source_seq RESTART WITH 19;

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