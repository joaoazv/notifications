# build
FROM maven:3.8.7-openjdk-18 as builder

WORKDIR /build
COPY . .
RUN mvn clean install

# run
FROM bellsoft/liberica-openjdk-alpine-musl:17
COPY --from=builder /build/target/*.jar ./app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]