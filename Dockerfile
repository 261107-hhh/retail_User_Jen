FROM openjdk:17
WORKDIR /app
ADD target/retail-user-docker.jar retail-user-docker.jar 
EXPOSE 9001
COPY . .
ENTRYPOINT ["java", "-jar","retail-user-docker.jar"]