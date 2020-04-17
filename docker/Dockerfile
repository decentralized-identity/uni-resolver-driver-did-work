# Dockerfile for universalresolver/driver-did-work

FROM universalresolver/base-ubuntu
MAINTAINER Jonathan Reynolds <jonathan.reynolds@workday.com>

# install dependencies

ARG GITHUB_READ_PACKAGES_OWNER
ARG GITHUB_READ_PACKAGES_TOKEN
ADD ./settings.xml /root/.m2/
ADD ./docker/VERSION .

# build driver-did-work

ADD . /opt/driver-did-work

RUN cd /opt/driver-did-work && mvn clean install package -N -DskipTests

# variables

ENV uniresolver_driver_did_work_apikey=sxVQUoDE015VhAs5ep4b57DFA5vT3zqvf1Dm1sGe
ENV uniresolver_driver_did_work_domain=https://credentials.id.workday.com

# done

EXPOSE 8080

RUN chmod a+rx /opt/driver-did-work/docker/run-driver-did-work.sh
CMD "/opt/driver-did-work/docker/run-driver-did-work.sh"
