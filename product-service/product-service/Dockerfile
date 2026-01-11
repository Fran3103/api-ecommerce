FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=target/product-service-0.0.1.jar
COPY ${JAR_FILE} app_product.jar
ENTRYPOINT ["java","-jar","/app_product.jar"]