FROM gradle:6.5.1-jdk8

COPY . /opt
WORKDIR /opt
RUN apt update
RUN apt install dos2unix
RUN dos2unix ./gradlew
RUN ./gradlew --no-daemon build

EXPOSE 8989

CMD ./gradlew --no-daemon run