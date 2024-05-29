FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/QuizApplicationQuestionService.v2-0.0.1-SNAPSHOT.jar QuizApplicationQuestionService.v2.jar
ENTRYPOINT ["java","-jar","/QuizApplicationQuestionService.v2.jar"]
EXPOSE 8082
