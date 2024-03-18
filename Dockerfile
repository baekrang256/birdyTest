FROM gradle:jdk21 as builder
LABEL authors="Seiyeon Cho"

# working direcotry
WORKDIR /app

# copy source
COPY . .

# build
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

# run
CMD ["./gradlew", "run"]
