FROM openjdk:17-jdk-alpine3.14

COPY "./target/autoshop.jar" "/application/autoshop.jar"

CMD ["java", "-jar", "/application/autoshop.jar"]
