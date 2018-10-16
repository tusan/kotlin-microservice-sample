FROM openjdk:8-jdk

COPY app.jar /src/app.jar
WORKDIR /src/
RUN ls
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]