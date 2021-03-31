FROM openjdk:11
ADD ./target/berlin-clock-api-0.0.1-SNAPSHOT.jar /usr/src/berlin-clock-api-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "berlin-clock-api-0.0.1-SNAPSHOT.jar"]