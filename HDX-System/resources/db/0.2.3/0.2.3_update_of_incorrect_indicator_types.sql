#to fix on CPS side issue https://github.com/OCHA-DAP/DAP-System/issues/218

update indicator_type set code='PCX140' where code='_Access to electricity (% of population)';
update indicator_type set code='PCH100' where code='_Children 1 year old immunized against measles, percentage';
update indicator_type set code='PVX080' where code='_emdat:no_homeless';
update indicator_type set code='PVX090' where code='_emdat:no_injured';
update indicator_type set code='PVX100' where code='_emdat:total_affected';
update indicator_type set code='PSE230' where code='_GNI, PPP (current international $)';
update indicator_type set code='PSE240' where code='_HDR:68606';
update indicator_type set code='TT027T' where code='_International migrant stock (% of population)';
update indicator_type set code='CG300' where code='_Land area (sq. km)';
update indicator_type set code='CG310' where code='_m49-name';
update indicator_type set code='PSE250' where code='_Net ODA received per capita (current US$)';
update indicator_type set code='PCH110' where code='_Number of infant deaths';
update indicator_type set code='PVN060' where code='_Population undernourished, millions';
update indicator_type set code='PVN070' where code='_Population undernourished, percentage';
update indicator_type set code='PSP120' where code='_Population, total';
update indicator_type set code='CG320' where code='_unterm:ISO Country alpha-2-code';
update indicator_type set code='PVH200' where code='_WPP2012_MORT_F02_CRUDE_DEATH_RATE';

#see if PCX060 is the right code
update indicator_type set code='PCX060' where code='_Internet users per 100 inhabitants';