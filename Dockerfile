FROM ubuntu:latest

RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y  \
    openjdk-17-jdk-headless && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

EXPOSE 5432

ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
ENV PATH $PATH:$JAVA_HOME/bin

RUN mkdir /submission
RUN chmod +x /submission/

WORKDIR /app
COPY target/CandidateSubmission-0.0.1-SNAPSHOT.jar .

EXPOSE 8089
CMD ["java", "-jar", "CandidateSubmission-0.0.1-SNAPSHOT.jar"]