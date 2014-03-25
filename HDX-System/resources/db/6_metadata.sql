INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Access to electricity is the percentage of population with access to electricity. Electrification data are collected from industry, national surveys and international sources.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Access to electricity (% of population)') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/EG.ELC.ACCS.ZS');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Access to electricity (% of population)') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Access to electricity (% of population)') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The proportion of 1 year-old children immunized against measles is the proportion of children under one year of age who have received at least one dose of measles-containing vaccine. 

Children under one year of age who have received a measles vaccine are estimated as the percentage of children aged 12–23 months who received at least one dose of measles vaccine any time before the survey or before the age of 12 months.

Measles-containing vaccines are live attenuated viral measles vaccines consisting of one dose given by the intramuscular or subcutaneous route, with the opportunity for a second dose at least one month after the first. It is generally recommended for children to be immunized against measles at the age of 9 months. In certain countries in Latin America and the Caribbean it is recommended for children to be immunized between the ages of 12 months and 15 months.

');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Children 1 year old immunized against measles, percentage') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'More information, including rationale, interpretation, sources, data collection, comments, limitation, and gender equality issues can be found at http://mdgs.un.org/unsd/mi/wiki/4-3-Proportion-of-1-year-old-children-immunised-against-measles.ashx');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Children 1 year old immunized against measles, percentage') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Children 1 year old immunized against measles, percentage') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of people needing immediate assistance for shelter due to a disaster');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_emdat:no_homeless') AND lower(s.code)=lower('emdat');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://www.emdat.be/explanatory-notes');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_emdat:no_homeless') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The EM-DAT database is protected by the law of June 30th, 1994 on copyrights and the law of August 31st, 1998 on the legal protection of databases. Access to the EM-DAT database is made available free of charge by the UCL. The reproduction and communication of the information from EM-DAT is authorized by any means and in all forms, provided that the source is clearly mentioned and as follows:
 
“EM-DAT: The OFDA/CRED International Disaster Database – www.emdat.be – Université catholique de Louvain – Brussels – Belgium.”

');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_emdat:no_homeless') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of People suffering from physical injuries, trauma or an illness requiring medical treatment as a direct result of a disaster.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_emdat:no_injured') AND lower(s.code)=lower('emdat');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://www.emdat.be/explanatory-notes');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_emdat:no_injured') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The EM-DAT database is protected by the law of June 30th, 1994 on copyrights and the law of August 31st, 1998 on the legal protection of databases. Access to the EM-DAT database is made available free of charge by the UCL. The reproduction and communication of the information from EM-DAT is authorized by any means and in all forms, provided that the source is clearly mentioned and as follows:
 
“EM-DAT: The OFDA/CRED International Disaster Database – www.emdat.be – Université catholique de Louvain – Brussels – Belgium.”

');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_emdat:no_injured') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'
Total affected includes the total number of injured, homeless, and affected people. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_emdat:total_affected') AND lower(s.code)=lower('emdat');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://www.emdat.be/explanatory-notes');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_emdat:total_affected') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The EM-DAT database is protected by the law of June 30th, 1994 on copyrights and the law of August 31st, 1998 on the legal protection of databases. Access to the EM-DAT database is made available free of charge by the UCL. The reproduction and communication of the information from EM-DAT is authorized by any means and in all forms, provided that the source is clearly mentioned and as follows:
 
“EM-DAT: The OFDA/CRED International Disaster Database – www.emdat.be – Université catholique de Louvain – Brussels – Belgium.”

');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_emdat:total_affected') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'PPP GNI (formerly PPP GNP) is gross national income (GNI) converted to international dollars using purchasing power parity rates. An international dollar has the same purchasing power over GNI as a U.S. dollar has in the United States. Gross national income is the sum of value added by all resident producers plus any product taxes (less subsidies) not included in the valuation of output plus net receipts of primary income (compensation of employees and property income) from abroad. Data are in current international dollars. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_GNI, PPP (current international $)') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/NY.GNP.MKTP.PP.CD');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_GNI, PPP (current international $)') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_GNI, PPP (current international $)') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'A composite measure reflecting inequality in achievements between women and men in three dimensions: reproductive health, empowerment and the labour market. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_HDR:68606') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'See Technical notes at http://hdr.undp.org/en/media/HDR_2012_EN_TechNotes.pdf for details on how the Gender Inequality Index is calculated. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_HDR:68606') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The information in the various pages of the UNDP Web site is issued by the United Nations Development Programme for general distribution. The information presented is protected under the Berne Convention for the Protection of Literature and Artistic works, under other international conventions and under national laws on copyright and neighboring rights. Extracts of the information in the Web site may be reviewed, reproduced or translated for research or private study but not for sale or for use in conjunction with commercial purposes. Any use of information in the Web site should be accompanied by an acknowledgment of UNDP as the source, citing the uniform resource locator (URL) of the article. Reproduction or translation of substantial portions of the Web site, or any use other than for educational or other non-commercial purposes, require explicit, prior authorization in writing. Applications and enquiries should be addressed to the programme responsible for the page used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_HDR:68606') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'International migrant stock is the number of people born in a country other than that in which they live. It also includes refugees. The data used to estimate the international migrant stock at a particular time are obtained mainly from population censuses. The estimates are derived from the data on foreign-born population--people who have residence in one country but were born in another country. When data on the foreign-born population are not available, data on foreign population--that is, people who are citizens of a country other than the country in which they reside--are used as estimates. After the breakup of the Soviet Union in 1991 people living in one of the newly independent countries who were born in another were classified as international migrants. Estimates of migrant stock in the newly independent states from 1990 on are based on the 1989 census of the Soviet Union. For countries with information on the international migrant stock for at least two points in time, interpolation or extrapolation was used to estimate the international migrant stock on July 1 of the reference years. For countries with only one observation, estimates for the reference years were derived using rates of change in the migrant stock in the years preceding or following the single observation available. A model was used to estimate migrants for countries that had no data. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_International migrant stock (% of population)') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/SM.POP.TOTL.ZS');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_International migrant stock (% of population)') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_International migrant stock (% of population)') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'This indicator is the percentage of individuals using the Internet.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Internet users per 100 inhabitants') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://mdgs.un.org/unsd/mi/wiki/8-16-Internet-users-per-100-inhabitants.ashx');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Internet users per 100 inhabitants') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Internet users per 100 inhabitants') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Land area is a country''s total area, excluding area under inland water bodies, national claims to continental shelf, and exclusive economic zones. In most cases the definition of inland water bodies includes major rivers and lakes.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Land area (sq. km)') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/AG.LND.TOTL.K2');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Land area (sq. km)') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Land area (sq. km)') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'List of two letter codes (ISO alpha-2) and the three letter codes (ISO alpha-3) as well as the United Nations used numerical code M49 for countries, dependencies, and other areas.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_m49-name') AND lower(s.code)=lower('m49');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_m49-name') AND lower(s.code)=lower('m49');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_m49-name') AND lower(s.code)=lower('m49');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Net official development assistance (ODA) per capita consists of disbursements of loans made on concessional terms (net of repayments of principal) and grants by official agencies of the members of the Development Assistance Committee (DAC), by multilateral institutions, and by non-DAC countries to promote economic development and welfare in countries and territories in the DAC list of ODA recipients; and is calculated by dividing net ODA received by the midyear population estimate. It includes loans with a grant element of at least 25 percent (calculated at a rate of discount of 10 percent). ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Net ODA received per capita (current US$)') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/DT.ODA.ODAT.PC.ZS');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Net ODA received per capita (current US$)') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Net ODA received per capita (current US$)') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of infants dying before reaching one year of age');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Number of infant deaths') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/SP.DYN.IMRT.IN');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Number of infant deaths') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Number of infant deaths') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The proportion of the population below the minimum level of dietary energy consumption referred to as the prevalence of undernourishment, is the percentage of the population that is undernourished or food deprived.

The undernourished or food deprived are those individuals whose food intake falls below the minimum level of dietary energy requirements.
');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Population undernourished, millions') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://mdgs.un.org/unsd/mdg/Metadata.aspx?IndicatorId=0&SeriesId=640');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Population undernourished, millions') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Population undernourished, millions') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The proportion of the population below the minimum level of dietary energy consumption referred to as the prevalence of undernourishment, is the percentage of the population that is undernourished or food deprived.

The undernourished or food deprived are those individuals whose food intake falls below the minimum level of dietary energy requirements.
');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Population undernourished, percentage') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://mdgs.un.org/unsd/mdg/SeriesDetail.aspx?srid=566&crid=');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Population undernourished, percentage') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Population undernourished, percentage') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Population, total refers to the total population.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Population, total') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/SP.POP.TOTL');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Population, total') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_Population, total') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Humanitarian Bulletin');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Humanitarian_Bulletin') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Humanitarian_Bulletin') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Humanitarian_Bulletin') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Humanitarian Dashboard');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Humanitarian_Dashboard') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Humanitarian_Dashboard') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Humanitarian_Dashboard') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Humanitarian Snapshot');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Humanitarian_Snapshot') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Humanitarian_Snapshot') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Humanitarian_Snapshot') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Infographic');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Infographic') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Infographic') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Infographic') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Key Messages');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Key_Messages') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Key_Messages') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Key_Messages') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Other');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Other') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Other') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Other') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Press Release');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Press_Release') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Press_Release') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Press_Release') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Press Review');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Press_Review') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Press_Review') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Press_Review') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Reference Map');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Reference_Map') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Reference_Map') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Reference_Map') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Situation Report');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Situation_Report') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Situation_Report') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Situation_Report') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Statement/Speech');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Statement/Speech') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Statement/Speech') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Statement/Speech') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of ReliefWeb reports flagged with ocha_product: Thematic Map');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Thematic_Map') AND lower(s.code)=lower('reliefweb-api');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://reliefweb.int/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Thematic_Map') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) ReliefWeb, the humanitarian information project of the United Nations Office for the Coordination of Humanitarian Affairs, maintains this web space (the “Space”) as a courtesy to those (hereafter referred to as “Users”) who may choose to access the Space. The information presented herein is for informational purposes only. ReliefWeb grants permission to Users to visit the Space and to download and copy the information, documents and materials (collectively, “Materials”) from the Space for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Materials within the Space.

(b) ReliefWeb administers this Space. All Material on this Space from United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Space do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_reliefweb_Thematic_Map') AND lower(s.code)=lower('reliefweb-api');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'List of two letter codes (ISO alpha-2) and the three letter codes (ISO alpha-3) as well as the United Nations used numerical code M49 for countries, dependencies, and other areas.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_unterm:ISO Country alpha-2-code') AND lower(s.code)=lower('unterm');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_unterm:ISO Country alpha-2-code') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_unterm:ISO Country alpha-2-code') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of deaths over a given period divided by the person-years lived by the population over that period. It is expressed as average annual number of deaths per 1,000 population.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_WPP2012_MORT_F02_CRUDE_DEATH_RATE') AND lower(s.code)=lower('esa-unpd-wpp2012');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/mortality.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_WPP2012_MORT_F02_CRUDE_DEATH_RATE') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('_WPP2012_MORT_F02_CRUDE_DEATH_RATE') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Wikipedia: geography');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD010') AND lower(s.code)=lower('wikipedia');




INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Part of our mission is to:
Empower and Engage people around the world to collect and develop educational content and either publish it under a free license or dedicate it to the public domain.
Disseminate this content effectively and globally, free of charge.

You are free to:
Read and Print our articles and other media free of charge.
Share and Reuse our articles and other media under free and open licenses.
Contribute To and Edit our various sites or Projects.

Under the following conditions:
Responsibility – You take responsibility for your edits (since we only host your content).
Civility – You support a civil environment and do not harass other users.
Lawful Behavior – You do not violate copyright or other laws.
No Harm – You do not harm our technology infrastructure.
Terms of Use and Policies – You adhere to the below Terms of Use and to the applicable community policies when you visit our sites or participate in our communities.

With the understanding that:
You License Freely Your Contributions – you generally must license your contributions and edits to our sites or Projects under a free and open license (unless your contribution is in the public domain).
No Professional Advice – the content of articles and other projects is for informational purposes only and does not constitute professional advice.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD010') AND lower(s.code)=lower('wikipedia');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Wikipedia: climate');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD030') AND lower(s.code)=lower('wikipedia');




INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Part of our mission is to:
Empower and Engage people around the world to collect and develop educational content and either publish it under a free license or dedicate it to the public domain.
Disseminate this content effectively and globally, free of charge.

You are free to:
Read and Print our articles and other media free of charge.
Share and Reuse our articles and other media under free and open licenses.
Contribute To and Edit our various sites or Projects.

Under the following conditions:
Responsibility – You take responsibility for your edits (since we only host your content).
Civility – You support a civil environment and do not harass other users.
Lawful Behavior – You do not violate copyright or other laws.
No Harm – You do not harm our technology infrastructure.
Terms of Use and Policies – You adhere to the below Terms of Use and to the applicable community policies when you visit our sites or participate in our communities.

With the understanding that:
You License Freely Your Contributions – you generally must license your contributions and edits to our sites or Projects under a free and open license (unless your contribution is in the public domain).
No Professional Advice – the content of articles and other projects is for informational purposes only and does not constitute professional advice.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD030') AND lower(s.code)=lower('wikipedia');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Wikipedia: economy');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD050') AND lower(s.code)=lower('wikipedia');




INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Part of our mission is to:
Empower and Engage people around the world to collect and develop educational content and either publish it under a free license or dedicate it to the public domain.
Disseminate this content effectively and globally, free of charge.

You are free to:
Read and Print our articles and other media free of charge.
Share and Reuse our articles and other media under free and open licenses.
Contribute To and Edit our various sites or Projects.

Under the following conditions:
Responsibility – You take responsibility for your edits (since we only host your content).
Civility – You support a civil environment and do not harass other users.
Lawful Behavior – You do not violate copyright or other laws.
No Harm – You do not harm our technology infrastructure.
Terms of Use and Policies – You adhere to the below Terms of Use and to the applicable community policies when you visit our sites or participate in our communities.

With the understanding that:
You License Freely Your Contributions – you generally must license your contributions and edits to our sites or Projects under a free and open license (unless your contribution is in the public domain).
No Professional Advice – the content of articles and other projects is for informational purposes only and does not constitute professional advice.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD050') AND lower(s.code)=lower('wikipedia');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Wikipedia: transport');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD060') AND lower(s.code)=lower('wikipedia');




INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Part of our mission is to:
Empower and Engage people around the world to collect and develop educational content and either publish it under a free license or dedicate it to the public domain.
Disseminate this content effectively and globally, free of charge.

You are free to:
Read and Print our articles and other media free of charge.
Share and Reuse our articles and other media under free and open licenses.
Contribute To and Edit our various sites or Projects.

Under the following conditions:
Responsibility – You take responsibility for your edits (since we only host your content).
Civility – You support a civil environment and do not harass other users.
Lawful Behavior – You do not violate copyright or other laws.
No Harm – You do not harm our technology infrastructure.
Terms of Use and Policies – You adhere to the below Terms of Use and to the applicable community policies when you visit our sites or participate in our communities.

With the understanding that:
You License Freely Your Contributions – you generally must license your contributions and edits to our sites or Projects under a free and open license (unless your contribution is in the public domain).
No Professional Advice – the content of articles and other projects is for informational purposes only and does not constitute professional advice.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD060') AND lower(s.code)=lower('wikipedia');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Wikipedia: education');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD070') AND lower(s.code)=lower('wikipedia');




INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Part of our mission is to:
Empower and Engage people around the world to collect and develop educational content and either publish it under a free license or dedicate it to the public domain.
Disseminate this content effectively and globally, free of charge.

You are free to:
Read and Print our articles and other media free of charge.
Share and Reuse our articles and other media under free and open licenses.
Contribute To and Edit our various sites or Projects.

Under the following conditions:
Responsibility – You take responsibility for your edits (since we only host your content).
Civility – You support a civil environment and do not harass other users.
Lawful Behavior – You do not violate copyright or other laws.
No Harm – You do not harm our technology infrastructure.
Terms of Use and Policies – You adhere to the below Terms of Use and to the applicable community policies when you visit our sites or participate in our communities.

With the understanding that:
You License Freely Your Contributions – you generally must license your contributions and edits to our sites or Projects under a free and open license (unless your contribution is in the public domain).
No Professional Advice – the content of articles and other projects is for informational purposes only and does not constitute professional advice.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD070') AND lower(s.code)=lower('wikipedia');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Wikipedia: demographics');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD080') AND lower(s.code)=lower('wikipedia');




INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Part of our mission is to:
Empower and Engage people around the world to collect and develop educational content and either publish it under a free license or dedicate it to the public domain.
Disseminate this content effectively and globally, free of charge.

You are free to:
Read and Print our articles and other media free of charge.
Share and Reuse our articles and other media under free and open licenses.
Contribute To and Edit our various sites or Projects.

Under the following conditions:
Responsibility – You take responsibility for your edits (since we only host your content).
Civility – You support a civil environment and do not harass other users.
Lawful Behavior – You do not violate copyright or other laws.
No Harm – You do not harm our technology infrastructure.
Terms of Use and Policies – You adhere to the below Terms of Use and to the applicable community policies when you visit our sites or participate in our communities.

With the understanding that:
You License Freely Your Contributions – you generally must license your contributions and edits to our sites or Projects under a free and open license (unless your contribution is in the public domain).
No Professional Advice – the content of articles and other projects is for informational purposes only and does not constitute professional advice.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD080') AND lower(s.code)=lower('wikipedia');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Wikipedia: religion');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD090') AND lower(s.code)=lower('wikipedia');




INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Part of our mission is to:
Empower and Engage people around the world to collect and develop educational content and either publish it under a free license or dedicate it to the public domain.
Disseminate this content effectively and globally, free of charge.

You are free to:
Read and Print our articles and other media free of charge.
Share and Reuse our articles and other media under free and open licenses.
Contribute To and Edit our various sites or Projects.

Under the following conditions:
Responsibility – You take responsibility for your edits (since we only host your content).
Civility – You support a civil environment and do not harass other users.
Lawful Behavior – You do not violate copyright or other laws.
No Harm – You do not harm our technology infrastructure.
Terms of Use and Policies – You adhere to the below Terms of Use and to the applicable community policies when you visit our sites or participate in our communities.

With the understanding that:
You License Freely Your Contributions – you generally must license your contributions and edits to our sites or Projects under a free and open license (unless your contribution is in the public domain).
No Professional Advice – the content of articles and other projects is for informational purposes only and does not constitute professional advice.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CD090') AND lower(s.code)=lower('wikipedia');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'This database was compiled over the years in response to diverse and wide-ranging demands of United Nations language staff for terminology and nomenclature. It is being put on the Internet to facilitate the efforts of people around the world who participate in the work of the United Nations but do not have access to the Secretariat''s intranet. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG020') AND lower(s.code)=lower('unterm');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG020') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG020') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'This database was compiled over the years in response to diverse and wide-ranging demands of United Nations language staff for terminology and nomenclature. It is being put on the Internet to facilitate the efforts of people around the world who participate in the work of the United Nations but do not have access to the Secretariat''s intranet. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG030') AND lower(s.code)=lower('unterm');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG030') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG030') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'List of two letter codes (ISO alpha-2) and the three letter codes (ISO alpha-3) as well as the United Nations used numerical code M49 for countries, dependencies, and other areas.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG060') AND lower(s.code)=lower('unterm');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG060') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG060') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'List of two letter codes (ISO alpha-2) and the three letter codes (ISO alpha-3) as well as the United Nations used numerical code M49 for countries, dependencies, and other areas.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG070') AND lower(s.code)=lower('unterm');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG070') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG070') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'This database was compiled over the years in response to diverse and wide-ranging demands of United Nations language staff for terminology and nomenclature. It is being put on the Internet to facilitate the efforts of people around the world who participate in the work of the United Nations but do not have access to the Secretariat''s intranet. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG080') AND lower(s.code)=lower('unterm');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG080') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG080') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'This database was compiled over the years in response to diverse and wide-ranging demands of United Nations language staff for terminology and nomenclature. It is being put on the Internet to facilitate the efforts of people around the world who participate in the work of the United Nations but do not have access to the Secretariat''s intranet. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG100') AND lower(s.code)=lower('unterm');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG100') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG100') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'This database was compiled over the years in response to diverse and wide-ranging demands of United Nations language staff for terminology and nomenclature. It is being put on the Internet to facilitate the efforts of people around the world who participate in the work of the United Nations but do not have access to the Secretariat''s intranet. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG120') AND lower(s.code)=lower('unterm');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG120') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG120') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'This database was compiled over the years in response to diverse and wide-ranging demands of United Nations language staff for terminology and nomenclature. It is being put on the Internet to facilitate the efforts of people around the world who participate in the work of the United Nations but do not have access to the Secretariat''s intranet. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG140') AND lower(s.code)=lower('unterm');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG140') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG140') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'For operational and analytical purposes, the World Bank’s main criterion for classifying economies is gross national income (GNI) per capita. In previous editions of our publications, this term was referred to as gross national product, or GNP. (Learn more about this change in terminology.) Based on its GNI per capita, every economy is classified as low income, middle income (subdivided into lower middle and upper middle), or high income. Other analytical groups based on geographic regions are also used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG150') AND lower(s.code)=lower('worldbank-lending-groups');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/about/country-classifications');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG150') AND lower(s.code)=lower('worldbank-lending-groups');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG150') AND lower(s.code)=lower('worldbank-lending-groups');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'AccuWeather is an American media company that provides for-profit weather forecasting services worldwide.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG260') AND lower(s.code)=lower('accuweather');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://en.wikipedia.org/wiki/AccuWeather');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG260') AND lower(s.code)=lower('accuweather');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'This database was compiled over the years in response to diverse and wide-ranging demands of United Nations language staff for terminology and nomenclature. It is being put on the Internet to facilitate the efforts of people around the world who participate in the work of the United Nations but do not have access to the Secretariat''s intranet. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG290') AND lower(s.code)=lower('unterm');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unterm.un.org/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG290') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CG290') AND lower(s.code)=lower('unterm');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'For a disaster to be entered into the database at least one of the following criteria must be fulfilled: Ten (10) or more people reported killed. Hundred (100) or more people reported affected. Declaration of a state of emergency. Call for international assistance.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH070') AND lower(s.code)=lower('emdat');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://www.emdat.be/explanatory-notes');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH070') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The EM-DAT database is protected by the law of June 30th, 1994 on copyrights and the law of August 31st, 1998 on the legal protection of databases. Access to the EM-DAT database is made available free of charge by the UCL. The reproduction and communication of the information from EM-DAT is authorized by any means and in all forms, provided that the source is clearly mentioned and as follows:
 
“EM-DAT: The OFDA/CRED International Disaster Database – www.emdat.be – Université catholique de Louvain – Brussels – Belgium.”

');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH070') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Persons confirmed as dead and persons missing and presumed dead (official figures when available).');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH080') AND lower(s.code)=lower('emdat');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://www.emdat.be/explanatory-notes');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH080') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The EM-DAT database is protected by the law of June 30th, 1994 on copyrights and the law of August 31st, 1998 on the legal protection of databases. Access to the EM-DAT database is made available free of charge by the UCL. The reproduction and communication of the information from EM-DAT is authorized by any means and in all forms, provided that the source is clearly mentioned and as follows:
 
“EM-DAT: The OFDA/CRED International Disaster Database – www.emdat.be – Université catholique de Louvain – Brussels – Belgium.”

');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH080') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'People requiring immediate assistance during a period of emergency; it can also include displaced or evacuated people.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH090') AND lower(s.code)=lower('emdat');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://www.emdat.be/explanatory-notes');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH090') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The EM-DAT database is protected by the law of June 30th, 1994 on copyrights and the law of August 31st, 1998 on the legal protection of databases. Access to the EM-DAT database is made available free of charge by the UCL. The reproduction and communication of the information from EM-DAT is authorized by any means and in all forms, provided that the source is clearly mentioned and as follows:
 
“EM-DAT: The OFDA/CRED International Disaster Database – www.emdat.be – Université catholique de Louvain – Brussels – Belgium.”

');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH090') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Several institutions have developed methodologies to quantify these losses in their specific domain. However, there is no standard procedure to determine a global figure for economic impact. Estimated damage are given (000’) US$.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH100') AND lower(s.code)=lower('emdat');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://www.emdat.be/explanatory-notes');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH100') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The EM-DAT database is protected by the law of June 30th, 1994 on copyrights and the law of August 31st, 1998 on the legal protection of databases. Access to the EM-DAT database is made available free of charge by the UCL. The reproduction and communication of the information from EM-DAT is authorized by any means and in all forms, provided that the source is clearly mentioned and as follows:
 
“EM-DAT: The OFDA/CRED International Disaster Database – www.emdat.be – Université catholique de Louvain – Brussels – Belgium.”

');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('CH100') AND lower(s.code)=lower('emdat');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Percentage of vaccines routinely administered in a country to protect children that are financed by the national government (including loans). EPI refers to the Expanded Programme on Immunization: The immunizations in this programme include those against TB, DPT, polio and measles, as well as protecting babies against neonatal tetanus by vaccination of pregnant women. Other vaccines (e.g. against hepatitis B or yellow fever) may be included in the programme in some countries.
');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCH090') AND lower(s.code)=lower('unicef-infobycountry');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://www.unicef.org/sowc01/tables/table3.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCH090') AND lower(s.code)=lower('unicef-infobycountry');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Mobile cellular telephone subscriptions are subscriptions to a public mobile telephone service using cellular technology, which provide access to the public switched telephone network. Post-paid and prepaid subscriptions are included. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX051') AND lower(s.code)=lower('mdgs');




INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX051') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Telephone lines are fixed telephone lines that connect a subscriber''s terminal equipment to the public switched telephone network and that have a port on a telephone exchange. Integrated services digital network channels ands fixed wireless subscribers are included.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX080') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/IT.MLT.MAIN.P2');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX080') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX080') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Mobile cellular telephone subscriptions are subscriptions to a public mobile telephone service using cellular technology, which provide access to the public switched telephone network. Post-paid and prepaid subscriptions are included. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX090') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/IT.CEL.SETS.P2');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX090') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX090') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Internet users are people with access to the worldwide network.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX100') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/IT.NET.USER.P2');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX100') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX100') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Logistics Performance Index overall score reflects perceptions of a country''s logistics based on efficiency of customs clearance process, quality of trade- and transport-related infrastructure, ease of arranging competitively priced shipments, quality of logistics services, ability to track and trace consignments, and frequency with which shipments reach the consignee within the scheduled time. The index ranges from 1 to 5, with a higher score representing better performance. Data are from Logistics Performance Index surveys conducted by the World Bank in partnership with academic and international institutions and private companies and individuals engaged in international logistics. 2009 round of surveys covered more than 5,000 country assessments by nearly 1,000 international freight forwarders. Respondents evaluate eight markets on six core dimensions on a scale from 1 (worst) to 5 (best). The markets are chosen based on the most important export and import markets of the respondent''s country, random selection, and, for landlocked countries, neighboring countries that connect them with international markets. Scores for the six areas are averaged across all respondents and aggregated to a single score using principal components analysis. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX130') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Details of the survey methodology and index construction methodology are in Arvis and others'' Connecting to Compete 2010: Trade Logistics in the Global Economy (2010). http://data.worldbank.org/indicator/LP.LPI.OVRL.XQ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX130') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PCX130') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'GDP per capita based on purchasing power parity (PPP). PPP GDP is gross domestic product converted to international dollars using purchasing power parity rates. An international dollar has the same purchasing power over GDP as the U.S. dollar has in the United States. GDP at purchaser''s prices is the sum of gross value added by all resident producers in the economy plus any product taxes and minus any subsidies not included in the value of the products. It is calculated without making deductions for depreciation of fabricated assets or for depletion and degradation of natural resources. Data are in current international dollars.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE030') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/NY.GDP.PCAP.PP.CD');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE030') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE030') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'GNI per capita based on purchasing power parity (PPP). PPP GNI is gross national income (GNI) converted to international dollars using purchasing power parity rates. An international dollar has the same purchasing power over GNI as a U.S. dollar has in the United States. GNI is the sum of value added by all resident producers plus any product taxes (less subsidies) not included in the valuation of output plus net receipts of primary income (compensation of employees and property income) from abroad. Data are in current international dollars.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE090') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/NY.GNP.PCAP.PP.CD');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE090') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE090') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'GDP per capita based on purchasing power parity (PPP). PPP GDP is gross domestic product converted to international dollars using purchasing power parity rates. An international dollar has the same purchasing power over GDP as the U.S. dollar has in the United States. GDP at purchaser''s prices is the sum of gross value added by all resident producers in the economy plus any product taxes and minus any subsidies not included in the value of the products. It is calculated without making deductions for depreciation of fabricated assets or for depletion and degradation of natural resources. Data are in constant 2005 international dollars.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE110') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/NY.GDP.PCAP.PP.KD');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE110') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The information in the various pages of the UNDP Web site is issued by the United Nations Development Programme for general distribution. The information presented is protected under the Berne Convention for the Protection of Literature and Artistic works, under other international conventions and under national laws on copyright and neighboring rights. Extracts of the information in the Web site may be reviewed, reproduced or translated for research or private study but not for sale or for use in conjunction with commercial purposes. Any use of information in the Web site should be accompanied by an acknowledgment of UNDP as the source, citing the uniform resource locator (URL) of the article. Reproduction or translation of substantial portions of the Web site, or any use other than for educational or other non-commercial purposes, require explicit, prior authorization in writing. Applications and enquiries should be addressed to the programme responsible for the page used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE110') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'GNI (formerly GNP) is the sum of value added by all resident producers plus any product taxes (less subsidies) not included in the valuation of output plus net receipts of primary income (compensation of employees and property income) from abroad. Data are in current U.S. dollars.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE120') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/NY.GNP.MKTP.CD');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE120') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE120') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Household final consumption expenditure (formerly private consumption) is the market value of all goods and services, including durable products (such as cars, washing machines, and home computers), purchased by households. It excludes purchases of dwellings but includes imputed rent for owner-occupied dwellings. It also includes payments and fees to governments to obtain permits and licenses. Here, household consumption expenditure includes the expenditures of nonprofit institutions serving households, even when reported separately by the country. Data are converted to constant 2005 international dollars using purchasing power parity rates.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE130') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/NE.CON.PRVT.PP.KD');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE130') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE130') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Age dependency ratio, old, is the ratio of older dependents (people older than 64) to the working-age population (those ages 15-64). Data are shown as the proportion of dependents per 100 working-age population. World Bank staff estimates from various sources including census reports, the United Nations Population Division''s World Population Prospects, national statistical offices, household surveys conducted by national agencies, and ICF International.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE140') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/SP.POP.DPND.OL');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE140') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE140') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Age dependency ratio, young, is the ratio of younger dependents (people younger than 15) to the working-age population(those ages 15-64). Data are shown as the proportion of dependents per 100 working-age population. World Bank staff estimates from various sources including census reports, the United Nations Population Division''s World Population Prospects, national statistical offices, household surveys conducted by national agencies, and ICF International.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE150') AND lower(s.code)=lower('world-bank');




INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE150') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Percentage of the population living below the international poverty line $1.25 (in purchasing power parity terms) a day');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE160') AND lower(s.code)=lower('hdrstats');




INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The information in the various pages of the UNDP Web site is issued by the United Nations Development Programme for general distribution. The information presented is protected under the Berne Convention for the Protection of Literature and Artistic works, under other international conventions and under national laws on copyright and neighboring rights. Extracts of the information in the Web site may be reviewed, reproduced or translated for research or private study but not for sale or for use in conjunction with commercial purposes. Any use of information in the Web site should be accompanied by an acknowledgment of UNDP as the source, citing the uniform resource locator (URL) of the article. Reproduction or translation of substantial portions of the Web site, or any use other than for educational or other non-commercial purposes, require explicit, prior authorization in writing. Applications and enquiries should be addressed to the programme responsible for the page used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE160') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Population below $1.25 a day is the percentage of the population living on less than $1.25 a day at 2005 international prices. As a result of revisions in PPP exchange rates, poverty rates for individual countries cannot be compared with poverty rates reported in earlier editions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE170') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'World Bank, Development Research Group. Data are based on primary household survey data obtained from government statistical agencies and World Bank country departments. Data for high-income economies are from the Luxembourg Income Study database. For more information and methodology, please see PovcalNet (http://iresearch.worldbank.org/PovcalNet/index.htm). http://data.worldbank.org/indicator/SI.POV.DDAY');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE170') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE170') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Inflation as measured by the consumer price index reflects the annual percentage change in the cost to the average consumer of acquiring a basket of goods and services that may be fixed or changed at specified intervals, such as yearly. The Laspeyres formula is generally used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE200') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/FP.CPI.TOTL.ZG');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE200') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE200') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Gini index measures the extent to which the distribution of income or consumption expenditure among individuals or households within an economy deviates from a perfectly equal distribution. A Lorenz curve plots the cumulative percentages of total income received against the cumulative number of recipients, starting with the poorest individual or household. The Gini index measures the area between the Lorenz curve and a hypothetical line of absolute equality, expressed as a percentage of the maximum area under the line. Thus a Gini index of 0 represents perfect equality, while an index of 100 implies perfect inequality.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE210') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/SI.POV.GINI');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE210') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE210') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The Human Development Index (HDI) is a composite statistic of life expectancy, education, and income indices used to rank countries into four tiers of human development. It was created by the Pakistani economist Mahbub ul Haq and the Indian economist Amartya Sen in 1990 and was published by the United Nations Development Programme.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE220') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://hdr.undp.org/en/statistics/hdi');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE220') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The information in the various pages of the UNDP Web site is issued by the United Nations Development Programme for general distribution. The information presented is protected under the Berne Convention for the Protection of Literature and Artistic works, under other international conventions and under national laws on copyright and neighboring rights. Extracts of the information in the Web site may be reviewed, reproduced or translated for research or private study but not for sale or for use in conjunction with commercial purposes. Any use of information in the Web site should be accompanied by an acknowledgment of UNDP as the source, citing the uniform resource locator (URL) of the article. Reproduction or translation of substantial portions of the Web site, or any use other than for educational or other non-commercial purposes, require explicit, prior authorization in writing. Applications and enquiries should be addressed to the programme responsible for the page used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSE220') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Total Population - Both Sexes. De facto population in a country, area or region as of 1 July of the year indicated. Figures are presented in thousands.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP010') AND lower(s.code)=lower('esa-unpd-wpp2012');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/population.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP010') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP010') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Average exponential rate of growth of the population over a given period.  It is calculated as ln(Pt/P0)/t where t is the length of the period. It is expressed as a percentage.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP050') AND lower(s.code)=lower('esa-unpd-wpp2012');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/population.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP050') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP050') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Population growth (annual %) is the exponential rate of growth of midyear population from year t-1 to t, expressed as a percentage. Derived from total population. Population source: (1) United Nations Population Division. World Population Prospects, (2) United Nations Statistical Division. Population and Vital Statistics Report (various years), (3) Census reports and other statistical publications from national statistical offices, (4) Eurostat: Demographic Statistics, (5) Secretariat of the Pacific Community: Statistics and Demography Programme, and (6) U.S. Census Bureau: International Database.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP060') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/SP.POP.GROW');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP060') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP060') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Age that divides the population in two parts of equal size, that is, there are as many persons with ages above the median as there are with ages below the median. It is expressed as years.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP070') AND lower(s.code)=lower('esa-unpd-wpp2012');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/population.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP070') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP070') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Population per square Kilometer.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP080') AND lower(s.code)=lower('esa-unpd-wpp2012');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/population.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP080') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP080') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Population density is midyear population divided by land area in square kilometers. Population is based on the de facto definition of population, which counts all residents regardless of legal status or citizenship--except for refugees not permanently settled in the country of asylum, who are generally considered part of the population of their country of origin. Land area is a country''s total area, excluding area under inland water bodies, national claims to continental shelf, and exclusive economic zones. In most cases the definition of inland water bodies includes major rivers and lakes. Food and Agriculture Organization and World Bank population estimates.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP090') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/EN.POP.DNST');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP090') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP090') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Percentage of Population Residing in Urban Areas by Major Area, Region and Country, 1950-2050');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP100') AND lower(s.code)=lower('esa-unpd-wup2011');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/population.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP100') AND lower(s.code)=lower('esa-unpd-wup2011');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP100') AND lower(s.code)=lower('esa-unpd-wup2011');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Population in urban agglomerations of more than one million is the percentage of a country''s population living in metropolitan areas that in 2000 had a population of more than one million people.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP110') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/population.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PSP110') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Public expenditure on education as % of GDP is the total public expenditure (current and capital) on education expressed as a percentage of the Gross Domestic Product (GDP) in a given year. Public expenditure on education includes government spending on educational institutions (both public and private), education administration, and transfers/subsidies for private entities (students/households and other privates entities).');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE010') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'https://data.undp.org/dataset/Public-expenditure-on-education-of-GDP-/bkr7-unqh');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE010') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The information in the various pages of the UNDP Web site is issued by the United Nations Development Programme for general distribution. The information presented is protected under the Berne Convention for the Protection of Literature and Artistic works, under other international conventions and under national laws on copyright and neighboring rights. Extracts of the information in the Web site may be reviewed, reproduced or translated for research or private study but not for sale or for use in conjunction with commercial purposes. Any use of information in the Web site should be accompanied by an acknowledgment of UNDP as the source, citing the uniform resource locator (URL) of the article. Reproduction or translation of substantial portions of the Web site, or any use other than for educational or other non-commercial purposes, require explicit, prior authorization in writing. Applications and enquiries should be addressed to the programme responsible for the page used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE010') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of years of schooling that a child of school entrance age can expect to receive if prevailing patterns of age-specific enrolment rates persist throughout the child’s life.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE030') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'https://data.undp.org/dataset/Expected-Years-of-Schooling-of-children-years-/qnam-f624');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE030') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The information in the various pages of the UNDP Web site is issued by the United Nations Development Programme for general distribution. The information presented is protected under the Berne Convention for the Protection of Literature and Artistic works, under other international conventions and under national laws on copyright and neighboring rights. Extracts of the information in the Web site may be reviewed, reproduced or translated for research or private study but not for sale or for use in conjunction with commercial purposes. Any use of information in the Web site should be accompanied by an acknowledgment of UNDP as the source, citing the uniform resource locator (URL) of the article. Reproduction or translation of substantial portions of the Web site, or any use other than for educational or other non-commercial purposes, require explicit, prior authorization in writing. Applications and enquiries should be addressed to the programme responsible for the page used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE030') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Percentage of the population ages 15 and older who can, with understanding, both read and write a short simple statement on their everyday life. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE040') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'https://data.undp.org/dataset/Adult-literacy-rate-both-sexes-aged-15-and-above-/x22y-8m6h');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE040') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The information in the various pages of the UNDP Web site is issued by the United Nations Development Programme for general distribution. The information presented is protected under the Berne Convention for the Protection of Literature and Artistic works, under other international conventions and under national laws on copyright and neighboring rights. Extracts of the information in the Web site may be reviewed, reproduced or translated for research or private study but not for sale or for use in conjunction with commercial purposes. Any use of information in the Web site should be accompanied by an acknowledgment of UNDP as the source, citing the uniform resource locator (URL) of the article. Reproduction or translation of substantial portions of the Web site, or any use other than for educational or other non-commercial purposes, require explicit, prior authorization in writing. Applications and enquiries should be addressed to the programme responsible for the page used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE040') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Average number of years of education received by people ages 25 and older, converted from education attainment levels using official durations of each level.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE110') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'https://data.undp.org/dataset/Mean-years-of-schooling-of-adults-years-/m67k-vi5c');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE110') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The information in the various pages of the UNDP Web site is issued by the United Nations Development Programme for general distribution. The information presented is protected under the Berne Convention for the Protection of Literature and Artistic works, under other international conventions and under national laws on copyright and neighboring rights. Extracts of the information in the Web site may be reviewed, reproduced or translated for research or private study but not for sale or for use in conjunction with commercial purposes. Any use of information in the Web site should be accompanied by an acknowledgment of UNDP as the source, citing the uniform resource locator (URL) of the article. Reproduction or translation of substantial portions of the Web site, or any use other than for educational or other non-commercial purposes, require explicit, prior authorization in writing. Applications and enquiries should be addressed to the programme responsible for the page used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE110') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The number of students enrolled in primary, secondary and tertiary levels of education, regardless of age, as a percentage of the population of theoretical school age for the three levels. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE120') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://stats.uis.unesco.org/unesco/tableviewer/document.aspx?ReportId=143');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE120') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The information in the various pages of the UNDP Web site is issued by the United Nations Development Programme for general distribution. The information presented is protected under the Berne Convention for the Protection of Literature and Artistic works, under other international conventions and under national laws on copyright and neighboring rights. Extracts of the information in the Web site may be reviewed, reproduced or translated for research or private study but not for sale or for use in conjunction with commercial purposes. Any use of information in the Web site should be accompanied by an acknowledgment of UNDP as the source, citing the uniform resource locator (URL) of the article. Reproduction or translation of substantial portions of the Web site, or any use other than for educational or other non-commercial purposes, require explicit, prior authorization in writing. Applications and enquiries should be addressed to the programme responsible for the page used.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE120') AND lower(s.code)=lower('hdrstats');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Net primary enrolment rate in primary education is the number of children of official primary school age (according to ISCED971) who are enrolled in primary education as a percentage of the total children of the official school age population. Total net primary enrolment rate also includes children of primary school age enrolled in secondary education. Where more than one system of primary education exists within the country the most widespread or common structure is used for determining the official school age group.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE130') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://mdgs.un.org/unsd/mdg/Metadata.aspx?IndicatorId=0&SeriesId=589');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE130') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVE130') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Estimates of per capita food supplies available for human consumption during the reference period in terms of quantity, caloric value, protein and fat content. Calorie supplies are reported in kilocalories (1 calorie = 4.19 kilojoules). Per capita supplies in terms of product weight are derived from the total supplies available for human consumption (i.e. Food) by dividing the quantities of Food by the total population actually partaking of the food supplies during the reference period, i.e. the present in-area (de facto) population within the present geographical boundaries of the country. In other words, nationals living abroad during the reference period are excluded, but foreigners living in the country are included. Adjustments are made wherever possible for part-time presence or absence, such as temporary migrants, tourists and refugees supported by special schemes (if it has not been possible to allow for the amounts provided by such schemes under imports). In almost all cases, the population figures used are the mid-year estimates published by the United Nations Population Division. Per capita supply figures shown in the commodity balances therefore represent only the average supply available for the population as a whole and do not necessarily indicate what is actually consumed by individuals. Even if they are taken as approximation to per capita consumption, it is important to note that the amount of food actually consumed may be lower than the quantity shown here, depending on the degree of losses of edible food and nutrients in the household, e.g. during storage, in preparation and cooking etc. In many cases commodities are not consumed in the primary form in which they are presented in the commodity balance, e.g. cereals enter the household mainly in processed form like flour, meal, husked or milled rice. To take this fact into account, the caloric value, the protein and fat content shown against primary commodities in the commodity balances have been derived by applying the appropriate food composition factors to the quantities of the processed commodities and not by multiplying the quantities shown in the commodity balance with the food composition factors relating to primary commodities.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVF020') AND lower(s.code)=lower('faostat3');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://faostat3.fao.org/faostat-gateway/go/to/browse/C/*/E');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVF020') AND lower(s.code)=lower('faostat3');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The average number of years of life expected by a hypothetical cohort of individuals who would be subject during all their lives to the mortality rates of a given period. It is expressed as years.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH010') AND lower(s.code)=lower('esa-unpd-wpp2012');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/mortality.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH010') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH010') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of deaths over a given period. Refers to five-year periods running from 1 July to 30 June of the initial and final years. Data are presented in thousands.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH050') AND lower(s.code)=lower('esa-unpd-wpp2012');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/mortality.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH050') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH050') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Adult mortality rate is the probability of dying between the ages of 15 and 60--that is, the probability of a 15-year-old dying before reaching age 60, if subject to current age-specific mortality rates between those ages.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH080') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/unpp/panel_population.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH080') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH080') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Adult mortality rate is the probability of dying between the ages of 15 and 60--that is, the probability of a 15-year-old dying before reaching age 60, if subject to current age-specific mortality rates between those ages.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH090') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/mortality.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH090') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH090') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Probability of dying between birth and exact age 5. It is expressed as average annual deaths per 1,000 births.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH100') AND lower(s.code)=lower('esa-unpd-wpp2012');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/mortality.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH100') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH100') AND lower(s.code)=lower('esa-unpd-wpp2012');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Probability of dying between birth and exactly age 5, expressed per 1,000 live births. Source: Inter-agency Group for Child Mortality Estimation (UNICEF, WHO, UN Population Division and World Bank) 2012');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH120') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'https://data.undp.org/dataset/Under-five-mortality-per-1-000-live-births-/a4ay-qce2');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH120') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The under-five mortality rate (U5MR) is the probability (expressed as a rate per 1,000 live births) of a child born in a specified year dying before reaching the age of five if subject to current age-specific mortality rates.

A live birth is the complete expulsion or extraction from its mother of a product of conception, irrespective of the duration of the pregnancy, which, after such separation, breathes or shows any other evidence of life—such as beating of the heart, pulsation of the umbilical cord, or definite movement of voluntary muscles—whether or not the umbilical cord has been cut or the placenta is attached. Each product of such a birth is considered a live birth.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH140') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://mdgs.un.org/unsd/mdg/Metadata.aspx?IndicatorId=0&SeriesId=561');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH140') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH140') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Probability of dying between birth and exact age 1. It is expressed as average annual deaths per 1,000 births');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH150') AND lower(s.code)=lower('esa-unpd-wpp2012');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://esa.un.org/wpp/Excel-Data/mortality.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH150') AND lower(s.code)=lower('esa-unpd-wpp2012');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Ratio of the number of maternal deaths to the number of live births in a given year, expressed per 100,000 live births. ');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH180') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'https://data.undp.org/dataset/Maternal-mortality-ratio-deaths-of-women-per-100-0/4gkx-mq89');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH180') AND lower(s.code)=lower('hdrstats');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The maternal mortality ratio (MMR) is the ratio of the number of maternal deaths during a given time period per 100,000 live births during the same time-period. A maternal death refers to a female death from any cause related to or aggravated by pregnancy or its management (excluding accidental or incidental causes) during pregnancy and childbirth or within 42 days of termination of pregnancy, irrespective of the duration and site of the pregnancy.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH190') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://mdgs.un.org/unsd/mdg/Metadata.aspx?IndicatorId=0&SeriesId=553');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH190') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVH190') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Number of airports by country');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVL010') AND lower(s.code)=lower('worldaerodata');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://worldaerodata.com/');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVL010') AND lower(s.code)=lower('worldaerodata');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Total road network includes motorways, highways, and main or national roads, secondary or regional roads, and all other roads in a country. A motorway is a road designed and built for motor traffic that separates the traffic flowing in opposite directions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVL030') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/IS.ROD.TOTL.KM');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVL030') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVL030') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Rail lines are the length of railway route available for train service, irrespective of the number of parallel tracks.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVL040') AND lower(s.code)=lower('world-bank');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://data.worldbank.org/indicator/IS.RRS.TOTL.KM');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVL040') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'You are free to copy, distribute, adapt, display or include the data in other products for commercial and noncommercial purposes at no cost subject to certain limitations summarized below.
You must include attribution for the data you use in the manner indicated in the metadata included with the data.
You must not claim or imply that The World Bank endorses your use of the data by or use The World Bank’s logo(s) or trademark(s) in conjunction with such use.
Other parties may have ownership interests in some of the materials contained on The World Bank Web site. For example, we maintain a list of some specific data within the Datasets that you may not redistribute or reuse without first contacting the original content provider, as well as information regarding how to contact the original content provider. Before incorporating any data in other products, please check the list: Terms of use: Restricted Data
The World Bank makes no warranties with respect to the data and you agree The World Bank shall not be liable to you in connection with your use of the data.
This is only a summary of the Terms of Use for Datasets Listed in The World Bank Data Catalogue. Please read the actual agreement that controls your use of the Datasets, which is available here: Terms of use for datasets. Also see World Bank Terms and Conditions.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVL040') AND lower(s.code)=lower('world-bank');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Estimated number of people at risk of undernourishment.
It is calculated by applying the estimated prevalence of undernourishment (see indicator V 7.1) to the total population (see indicator V_A1 in each period. Data for a 3-year period have been used for the estimation of the prevalence of undernourishment.

The reference periods are those periods for which data were available.

Baseline refers to 1993–95 for Former USSR Republics, Former Yugoslavian Republic and Former Czechoslovakia for proportion of undernourished and 1990-92 population-based for number of undernourished.

China, refers to China Mainland, Hong Kong SAR, Macao SAR and Taiwan Province.

Although not listed separately, provisional estimates for Afghanistan, Democratic Rep. of Congo, Iraq, Myanmar, Papua New Guinea and Somalia have been included in the relevant regional aggregates.

Eritrea and Ethiopia were not separate entities in 1990-1992 but estimates of the number and proportion of undernourished in the former Ethiopia PDR are included in regional and subregional aggregates for that period.

A dash (-) represents data less than 5%.

');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVN010') AND lower(s.code)=lower('fao-foodsec');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://www.fao.org/docrep/005/Y4249E/y4249e06.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVN010') AND lower(s.code)=lower('fao-foodsec');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Prevalence of (moderately and severely) underweight children is the percentage of children aged 0-59 months whose weights for age are less than two standard deviations below the median weight for age of the international reference population. The international reference population, often referred to as the NCHS/WHO reference population, was formulated by the National Center for Health Statistics (NCHS) as a reference for the United States and later adopted by the World Health Organization (WHO).

The NCHS/WHO reference standard represents the distribution of height and weight by age and sex in a well-nourished population. In a well-nourished population, 2.3 percent of children fall below minus two standard deviations.

A new standard reference population, the WHO Child Growth Standards, was released in April 2006 and is also being used to estimate underweight prevalence (see Comments and Limitations below).

Percentage of children under five that are underweight =        (Number of children under age five that fall below minus two standard deviations from the median weight for age of the NCHS/WHO standard (moderate and severe))*100/ Total number of children under age five that were weighted.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVN050') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://mdgs.un.org/unsd/mdg/Metadata.aspx?IndicatorId=0&SeriesId=559');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVN050') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVN050') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The proportion of the population using an improved drinking water source, total, urban, and rural, is the percentage of the population who use any of the following types of water supply for drinking: piped water into dwelling, plot or yard; public tap/standpipe; borehole/tube well; protected dug well; protected spring; rainwater collection and bottled water (if a secondary available source is also improved). It does not include unprotected well, unprotected spring, water provided by carts with small tanks/drums, tanker truck-provided water and bottled water (if secondary source is not an improved source) or surface water taken directly from rivers, ponds, streams, lakes, dams, or irrigation channels. Definitions and a detailed description of these facilities can be found at the website of the WHO/UNICEF Joint Monitoring Programme for Water Supply and Sanitation at www.wssinfo.org.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVW010') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://unstats.un.org/unsd/mdg/Metadata.aspx?IndicatorId=0&SeriesId=665');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVW010') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVW010') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The proportion of the population using an improved sanitation facility, total, urban, rural, is the percentage of the population with access to facilities that hygienically separate human excreta from human contact. Improved facilities include flush/pour flush toilets or latrines connected to a sewer, -septic tank, or -pit, ventilated improved pit latrines, pit latrines with a slab or platform of any material which covers the pit entirely, except for the drop hole and composting toilets/latrines. Unimproved facilities include public or shared facilities of an otherwise acceptable type, flush/pour-flush toilets or latrines which discharge directly into an open sewer or ditch, pit latrines without a slab, bucket latrines, hanging toilets or latrines which directly discharge in water bodies or in the open and the practice of open defecation in the bush, field or bodies or water. Definitions and a detailed description of these facilities can be found at the website of the WHO/UNICEF Joint Monitoring Programme for Water Supply and Sanitation at www.wssinfo.org.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVW040') AND lower(s.code)=lower('mdgs');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://mdgs.un.org/unsd/mdg/Metadata.aspx?IndicatorId=0&SeriesId=668');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVW040') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The use of this web site constitutes agreement with the following terms and conditions:

(a) The United Nations maintains this web site (the “Site”) as a courtesy to those who may choose to access the Site (“Users”). The information presented herein is for informative purposes only. The United Nations grants permission to Users to visit the Site and to download and copy the information, documents and materials (collectively, “Materials”) from the Site for the User’s personal, non-commercial use, without any right to resell or redistribute them or to compile or create derivative works therefrom, subject to the terms and conditions outlined below, and also subject to more specific restrictions that may apply to specific Material within this Site.

(b) The United Nations administers this Site. All Material on this Site from the United Nations appears subject to the present Terms and Conditions.

(c) Unless expressly stated otherwise, the findings, interpretations and conclusions expressed in the Materials on this Site are those of the various United Nations staff members, consultants and advisers to the United Nations Secretariat who prepared the work and do not necessarily represent the views of the United Nations or its Member States.

Disclaimers

Materials provided on this Site are provided “as is”, without warranty of any kind, either express or implied, including, without limitation, warranties of merchantability, fitness for a particular purpose and non-infringement. The United Nations specifically does not make any warranties or representations as to the accuracy or completeness of any such Materials. The United Nations periodically adds, changes, improves or updates the Materials on this Site without notice. Under no circumstances shall the United Nations be liable for any loss, damage, liability or expense incurred or suffered that is claimed to have resulted from the use of this Site, including, without limitation, any fault, error, omission, interruption or delay with respect thereto. The use of this Site is at the User’s sole risk. Under no circumstances, including but not limited to negligence, shall the United Nations or its affiliates be liable for any direct, indirect, incidental, special or consequential damages, even if the United Nations has been advised of the possibility of such damages.

The User specifically acknowledges and agrees that the United Nations is not liable for any conduct of any User.

This site may contain advice, opinions and statements of various information providers. The United Nations does not represent or endorse the accuracy or reliability of any advice, opinion, statement or other information provided by any information provider, any User of this Site or any other person or entity. Reliance upon any such advice, opinion, statement, or other information shall also be at the User’s own risk. Neither the United Nations nor its affiliates, nor any of their respective agents, employees, information providers or content providers, shall be liable to any User or anyone else for any inaccuracy, error, omission, interruption, deletion, defect, alteration of or use of any content herein, or for its timeliness or completeness, nor shall they be liable for any failure of performance, computer virus or communication line failure, regardless of cause, or for any damages resulting therefrom.

As a condition of use of this Site, the User agrees to indemnify the United Nations and its affiliates from and against any and all actions, claims, losses, damages, liabilities and expenses (including reasonable attorneys’ fees) arising out of the User’s use of this Site, including, without limitation, any claims alleging facts that if true would constitute a breach by the User of these Terms and Conditions. If the User is dissatisfied with any Material on this Site or with any of its Terms and Conditions of Use, the User’s sole and exclusive remedy is to discontinue using the Site.

This Site may contain links and references to third-party web sites. The linked sites are not under the control of the United Nations, and the United Nations is not responsible for the content of any linked site or any link contained in a linked site. The United Nations provides these links only as a convenience, and the inclusion of a link or reference does not imply the endorsement of the linked site by the United Nations.

If this Site contains bulletin boards, chat rooms, access to mailing lists or other message or communication facilities (collectively, “Forums”), the User agrees to use the Forums only to send and receive messages and materials that are proper and related to the particular Forum. By way of example and not as a limitation, the User agrees that when using a Forum, he or she shall not do any of the following: 
(a) Defame, abuse, harass, stalk, threaten or otherwise violate the legal rights (such as rights of privacy and publicity) of others;

(b) Publish, post, distribute or disseminate any defamatory, infringing, obscene, indecent or unlawful material or information;

(c) Upload or attach files that contain software or other material protected by intellectual property laws (or by rights of privacy and publicity) unless the User owns or controls the rights thereto or has received all consents therefor as may be required by law;

(d) Upload or attach files that contain viruses, corrupted files or any other similar software or programs that may damage the operation of another’s computer;

(e) Delete any author attributions, legal notices or proprietary designations or labels in any file that is uploaded;

(f) Falsify the origin or source of software or other material contained in a file that is uploaded;

(g) Advertise or offer to sell any goods or services, or conduct or forward surveys, contests or chain letters, or download any file posted by another user of a Forum that the User knows, or reasonably should know, cannot be legally distributed in such manner.

The User acknowledges that all Forums and discussion groups are public and not private communications. Further, the User acknowledges that chats, postings, conferences, e-mails and other communications by other Users are not endorsed by the United Nations, and that such communications shall not be considered to have been reviewed, screened or approved by the United Nations. The United Nations reserves the right to remove, for any reason and without notice, any content of the Forums received from Users, including, without limitation, e-mail and bulletin board postings.

The boundaries and names shown and the designations used on the maps on this site do not imply official endorsement or acceptance by the United Nations.

Preservation of immunities

Nothing herein shall constitute or be considered to be a limitation upon or a waiver of the privileges and immunities of the United Nations, which are specifically reserved.

General

The United Nations reserves its exclusive right in its sole discretion to alter, limit or discontinue the Site or any Materials in any respect. The United Nations shall have no obligation to take the needs of any User into consideration in connection therewith.

The United Nations reserves the right to deny in its sole discretion any user access to this Site or any portion thereof without notice.

No waiver by the United Nations of any provision of these Terms and Conditions shall be binding except as set forth in writing and signed by its duly authorized representative.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'TERMS_OF_USE',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVW040') AND lower(s.code)=lower('mdgs');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The GNA Final Index lists countries in ranking order. The GNA Vulnerability Index and the GNA Crisis Index are shown, as well as the various dimensions. Each dimension has a value that is the result of combining indicators. Note that all values are the result of ranking countries. If countries are in the top quartile (25%), they score 3; in the bottom quartile, they score 1; in the middle 2 quartiles, they score 2.  For the calculation methods see the Methodological notes on http://ec.europa.eu/echo/policies/strategy_en.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX010') AND lower(s.code)=lower('echo');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Index value is taken directly from the source dataset.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'METHODOLOGY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX010') AND lower(s.code)=lower('echo');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://ec.europa.eu');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX010') AND lower(s.code)=lower('echo');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The GNA Final Index lists countries in ranking order. The GNA Vulnerability Index and the GNA Crisis Index are shown, as well as the various dimensions. Each dimension has a value that is the result of combining indicators. Note that all values are the result of ranking countries. If countries are in the top quartile (25%), they score 3; in the bottom quartile, they score 1; in the middle 2 quartiles, they score 2.  For the calculation methods see the Methodological notes on http://ec.europa.eu/echo/policies/strategy_en.htm');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX020') AND lower(s.code)=lower('echo');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Index value is taken directly from the source dataset.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'METHODOLOGY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX020') AND lower(s.code)=lower('echo');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://ec.europa.eu');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX020') AND lower(s.code)=lower('echo');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'The Armed Conflict Location and Event Database (ACLED) project codes reported information on the exact location, date, and other characteristics of politically violent events in unstable and warring states. ACLED focuses specifically on: 
- Tracking rebel, militia and government activity over time and space 
- Locating rebel group bases, headquarters, strongholds and presence 
- Distinguishing between territorial transfers of military control from governments 
to rebel groups and vice versa 
- Recording violent acts between militias 
- Collecting information on rioting and protesting 
- Non-violent events that are crucial to the dynamics of political violence (e.g. rallies, recruitment drives, peace talks, high-level arrests) 
ACLED data cover all countries on the African continent from 1997-present. Real-time data is available for African states. Data for additional countries including Haiti, Laos, Cambodia, Nepal, Myanmar are available from 1997-early 2010. Data for Afghanistan and Pakistan is available from 2006-2009 and Lebanon from 2006-2012.
');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX040') AND lower(s.code)=lower('acled');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Total number of incidents of all types are summed by country by year.  Non-violent incident categories are excluded from the sum.');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'METHODOLOGY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX040') AND lower(s.code)=lower('acled');
INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'http://www.acleddata.com');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'MORE_INFO',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX040') AND lower(s.code)=lower('acled');


INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Can''t find this series. Only 2010 in the scraped data. EM-DAT also provides this data');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX060') AND lower(s.code)=lower('hdi-disaster');






INSERT INTO text(id, default_value) VALUES (nextval('text_seq'),'Can''t find this series. Only 2010 in the scraped data. EM-DAT also provides this data');
INSERT INTO hdx_additional_data(id,indicator_type_id,source_id,entry_key, entry_value_text_id)  SELECT nextval('hdx_additional_data_seq'),t.id, s.id, 'DATASET_SUMMARY',currval('text_seq') FROM indicator_type t, source s WHERE lower(t.code)=lower('PVX070') AND lower(s.code)=lower('hdrstats');
