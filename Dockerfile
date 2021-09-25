FROM openjdk:16
LABEL maintainer = "Odunayo"
ADD target/BlogApi-0.0.1-SNAPSHOT.jar springboot-docker-blog.jar
ENTRYPOINT ["java", "-jar", "springboot-docker-blog.jar"]
