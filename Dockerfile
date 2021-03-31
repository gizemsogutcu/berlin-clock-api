FROM adoptopenjdk/openjdk11:alpine as compile
RUN apk add maven
WORKDIR /app
COPY . /app/
RUN mvn -f pom.xml clean package -DskipTests

FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=compile "/app/target/berlin-clock-api-0.0.1-SNAPSHOT.jar" /usr/share/
ENTRYPOINT ["java", "-jar", "/usr/share/berlin-clock-api-0.0.1-SNAPSHOT.jar"]