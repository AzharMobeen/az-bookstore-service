FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=build/libs/*.jar
RUN echo ${DEPENDENCY}
COPY ${DEPENDENCY} /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]