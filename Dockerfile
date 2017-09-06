FROM maven:3.2-jdk-8-onbuild
WORKDIR /usr/src/app
CMD ["mvn", "exec:java"]
