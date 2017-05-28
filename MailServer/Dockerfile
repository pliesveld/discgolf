FROM openjdk:alpine

ENV SMTP_PORT="${SMTP_PORT:-2525}"
EXPOSE ${SMTP_PORT}

RUN mkdir -p /opt/service/email/
COPY ./fakeSMTP-2.1-SNAPSHOT.jar /opt/service/email
WORKDIR /opt/service/email
CMD java -jar fakeSMTP-2.1-SNAPSHOT.jar -m -b -s -p $SMTP_PORT

