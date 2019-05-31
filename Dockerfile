FROM oracle/graalvm-ce:1.0.0-rc11 as graalvm
COPY . /home/app/micronaut-realworld-example-app
WORKDIR /home/app/micronaut-realworld-example-app
RUN ./build-native-image.sh

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/micronaut-realworld-example-app .
ENTRYPOINT ["./micronaut-realworld-example-app"]
