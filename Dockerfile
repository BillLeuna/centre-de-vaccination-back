# Dockerfile
FROM openjdk:17

WORKDIR /app

# Copie du JAR généré (artefact)
COPY build/libs/CentreDeVaccination-0.0.1-SNAPSHOT.jar app.jar

# CMD par défaut pour le JAR
CMD ["java", "-jar", "app.jar"]

EXPOSE 8090