FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/springboot-mongodb-k8s-parth-0.0.1-SNAPSHOT app.jar
ENTRYPOINT ["java","-jar","app.jar"]