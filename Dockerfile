FROM maven:3.8.3-openjdk-11

WORKDIR /app

COPY pom.xml .
COPY src ./src

CMD ["mvn", "test"]