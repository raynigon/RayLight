FROM node

COPY frontend/ .
RUN npm install &&\
    npm build &&\
    npm test

FROM maven:3-jdk-12

COPY backend/ .
COPY --from=0 dist/RayLight src/main/resources/web/
RUN mvn install

FROM openjdk:12-alpine
