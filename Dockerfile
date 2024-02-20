FROM openjdk:17-slim

EXPOSE 8080

WORKDIR /app

COPY ./build/libs/factsheet-admin-api.jar /app/factsheet-admin-api.jar

ENTRYPOINT ["java", "-jar", "factsheet-admin-api.jar"]
