#!/bin/sh

cd /opt/driver-did-work/
mvn --settings settings.xml jetty:run -P war
