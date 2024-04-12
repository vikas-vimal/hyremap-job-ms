FROM openjdk:23-slim
COPY target/hyremap-job-ms-0.0.1-SNAPSHOT.jar hyremap-job-ms-0.0.1.jar
ENTRYPOINT ["java","-jar","/hyremap-job-ms-0.0.1.jar"]
