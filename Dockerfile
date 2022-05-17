FROM openjdk:17-jdk-alpine3.14

COPY "./target/autoshop_test.jar" "/application/autoshop._testjar"

CMD ["java", "-jar", "/application/autoshop_test.jar"]
