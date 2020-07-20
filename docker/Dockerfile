# Dockerfile for universalresolver/driver-did-work

FROM maven:3-jdk-11
MAINTAINER Jonathan Reynolds <jonathan.reynolds@workday.com>

USER root

ENV PATH $JAVA_HOME/bin:$PATH

# install dependencies

RUN mvn org.apache.maven.plugins:maven-dependency-plugin:3.1.1:get -Dartifact=org.eclipse.jetty:jetty-maven-plugin:9.4.18.v20190429
RUN mvn org.apache.maven.plugins:maven-dependency-plugin:3.1.1:get -DrepoUrl=https://repo.maven.apache.org/maven2 -Dartifact=org.apache.maven.plugins:maven-war-plugin:3.2.2

# build driver-did-work

ADD . /opt/driver-did-work
ADD ./docker/VERSION .
RUN cd /opt/driver-did-work && mvn clean install package -N -DskipTests

# variables

ENV uniresolver_driver_did_work_apikey=sxVQUoDE015VhAs5ep4b57DFA5vT3zqvf1Dm1sGe
ENV uniresolver_driver_did_work_domain=https://credentials.id.workday.com

# done

EXPOSE 8080

RUN chmod a+rx /opt/driver-did-work/docker/run-driver-did-work.sh
CMD "/opt/driver-did-work/docker/run-driver-did-work.sh"
