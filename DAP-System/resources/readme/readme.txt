#how to build the project
This is a maven project. The build command is "mvn clean install"

#profiles
by default, the project is built for production
It is possible to have a different configuration. This is done by using maven profiles
for instance, running "mvn clean install -Pseustachi", the project is built with the seustachi profile

#DB setup
To prepare the environment, 3 SQL scripts are needed (TODO, a sys-admin will probably want to decide which user should run these scripts)
1 - db.sql (prepares the db) 
2 - schema.sql (prepares the data model)
3 - sample.sql (optional, setting up some users)

#additional configuration
1 folder needs to be set up for logs  (default: /opt/dap/logs)
1 folder needs to be set up for config(default: /opt/dap/config)