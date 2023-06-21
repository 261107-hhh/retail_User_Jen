FROM openjdk:17
EXPOSE 9001
ADD target/retail-user-docker.jar retail-user-docker.jar 
ENTRYPOINT ["java", "-jar","/retail-user-docker.jar"]