FROM openjdk:8-jdk

COPY . /src/
WORKDIR /src/
RUN ls && ./gradlew clean build

EXPOSE 8080

CMD ["java", "-jar", "build/libs/app.jar"]