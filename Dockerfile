LABEL authors="Seiyeon Cho"

#build stage image
FROM gradle:jdk21-alpine as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew shadowJar

# run stage image
FROM openjdk:21-jdk
COPY --from=build /home/gradle/src/build/libs/birdy.jar /app/
WORKDIR /app
CMD ["java", "-jar", "birdy.jar"]
