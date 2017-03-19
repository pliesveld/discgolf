FROM mongo:latest

RUN mkdir -p /data/scripts

COPY mongodb.conf /etc/

VOLUME /data/db2
CMD ["mongod", "--config", "/etc/mongodb.conf", "--smallfiles"]

