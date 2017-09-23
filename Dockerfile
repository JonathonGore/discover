FROM maven:3.2-jdk-8-onbuild

WORKDIR /usr/src/app

ADD . /usr/src/app

ENV DISC_ES_HOST elasticsearch

CMD ["mvn", "exec:java"]
