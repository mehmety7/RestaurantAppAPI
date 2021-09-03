FROM openjdk:8
WORKDIR /app
ADD target/restaurantapp-0.0.1-SNAPSHOT.jar restaurantapp-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "restaurantapp-0.0.1-SNAPSHOT.jar"]