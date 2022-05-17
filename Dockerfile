FROM openjdk:17-jdk-alpine3.14

COPY "./target/autoshop.jar" "/application/autoshop_test.jar"

CMD ["java", "-jar", "/application/autoshop_test.jar"]
