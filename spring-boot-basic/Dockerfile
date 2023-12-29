FROM alpine:latest

ENV PATH=$PATH:/opt/java/jdk1.8.0_321/bin

RUN apk add --no-cache curl && \
    mkdir /opt/java && \
    curl https://openjdk-sources.osci.io/openjdk8/openjdk8u392-b08.tar.xz | tar -xz -C /opt/java/
