#Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

#set the working directory
WORKDIR /app

#Copy the built jar file into the container
COPY target/productcatalog-0.0.1-SNAPSHOT.jar app.jar

#Run the jar file
ENTRYPOINT ["java","-jar", "app.jar"]