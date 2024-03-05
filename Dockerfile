FROM ghcr.io/graalvm/graalvm-community:21 AS build

USER root

RUN microdnf install findutils

WORKDIR /code

COPY . .

RUN ./gradlew nativeCompile -x test

FROM debian:12.4-slim

WORKDIR /work/

COPY --from=build /code/core/build/native/nativeCompile/* /work

RUN chmod 775 /work

EXPOSE 8080

CMD ["./core", "-Xmx170M", "-Xms50M", "-Xss156k"]