FROM openjdk:11-oraclelinux8

ARG JAR_FILE

RUN mkdir -p /apps
COPY ./target/${JAR_FILE} /apps/app.jar
COPY ./entrypoint.sh /apps/entrypoint.sh
COPY ./config-repo/ /apps/config-repo/
RUN chmod +x /apps/entrypoint.sh

CMD ["/apps/entrypoint.sh"]
