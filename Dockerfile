# Dockerfile
FROM openjdk:21

WORKDIR /app

# Copie du JAR généré
COPY build/libs/CentreDeVaccination-0.0.1-SNAPSHOT.jar app.jar

# CMD par défaut pour le JAR
CMD ["java", "-jar", "app.jar"]
