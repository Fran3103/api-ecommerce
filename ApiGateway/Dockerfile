FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=target/ApiGateway-0.0.1.jar
COPY ${JAR_FILE} app_apigateway.jar
ENTRYPOINT ["java","-jar","/app_apigateway.jar"]