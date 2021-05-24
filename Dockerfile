FROM openjdk:8-jdk-alpine

LABLE maintainer="sjoh@inzent.com"

ADD build/libs/sjoh-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT ["java","-jar","/app.jar"]
