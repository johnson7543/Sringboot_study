# Build Stage for Spring boot application image
FROM openjdk:11 as build
EXPOSE 8080
ADD /target/products_page_demo-0.0.1-SNAPSHOT.jar products_page_demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","products_page_demo-0.0.1-SNAPSHOT.jar"]