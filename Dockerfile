#build stage image
FROM gradle:jdk21-alpine as builder
LABEL authors="Seiyeon Cho"
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN chmod +x ./gradlew
RUN ./gradlew shadowJar

# run stage image
FROM openjdk:21-jdk
COPY --from=builder /home/gradle/src/build/libs/birdy.jar /app/
WORKDIR /app
CMD ["java", "-jar", "birdy.jar"]
