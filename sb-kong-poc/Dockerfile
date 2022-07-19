FROM openjdk:8
ADD target/sb-kong-poc.jar sb-kong-poc.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sb-kong-poc.jar"]docker build -t