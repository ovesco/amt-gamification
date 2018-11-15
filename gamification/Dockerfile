#FROM maven as build
#ARG LOCAL_ENV=false
#COPY ./src /app/
#RUN mkdir /app/target/
#RUN touch /app/target/game.war
#WORKDIR /app
# Run the following only if we are not in Dev Environment:
#RUN test $LOCAL_ENV = "false" && mvn clean install


FROM payara/server-full:5.183

MAINTAINER Guidoux Vincent <vincent.guidoux1@heig-vd.ch>


COPY target/game.war /opt/payara5/deployments/

ADD drivers/mysql-connector-java-8.0.13.jar glassfish/lib/
COPY drivers/grizzly-npn-bootstrap.jar glassfish/lib/

RUN echo "AS_ADMIN_PASSWORD=admin" > /tmp/pypw.txt && \
	./bin/asadmin start-domain domain1 && \
	./bin/asadmin --interactive=false --user admin --passwordfile=/tmp/pypw.txt create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --property user=user:password=test:databaseName=gamification:useSSL=false:serverName=db:port=3306 mysql && \
	
	./bin/asadmin --interactive=false --user admin --passwordfile=/tmp/pypw.txt create-jdbc-resource --connectionpoolid mysql jdbc/gamification && \
	
	./bin/asadmin --interactive=false --user admin --passwordfile=/tmp/pypw.txt create-javamail-resource --mailhost smtp.gmail.com --mailuser amt.gamification  --fromaddress amt.gamification@gmail.com --storeprotocol imap --storeprotocolclass com.sun.mail.imap.IMAPStore --transprotocol smtp --transprotocolclass com.sun.mail.smtp.SMTPTransport --property mail.smtp.user=amt.gamification@gmail.com:mail.smtp.password=gamification2018:mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory:mail.smtp.socketFactory.port=465:mail.smtp.port=465:mail.smtp.auth=true mail/gamification