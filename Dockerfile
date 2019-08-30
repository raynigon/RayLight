FROM node

WORKDIR /ws/
COPY Frontend/ .
RUN npm ci &&\
    npm run lint &&\
    npm run build

FROM openjdk:12-alpine

WORKDIR /ws/
COPY Backend/ .
COPY --from=0 /ws/dist/RayLight src/main/resources/web/
RUN ./mvnw install -DskipTests

FROM openjdk:12-alpine

EXPOSE 8080
WORKDIR /app/
COPY --from=1 /ws/target/RayLight-*.jar RayLight.jar
RUN chmod 775 RayLight.jar

CMD ["java", "-jar", "RayLight.jar"]