FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=target/shoppingcart-service-0.0.1.jar
COPY ${JAR_FILE} app_shoppingcart.jar
ENTRYPOINT ["java","-jar","/app_shoppingcart.jar"]