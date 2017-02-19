FROM mongo:latest

RUN mkdir -p /data/scripts

VOLUME /data/db2
CMD ["mongod", "--config", "/etc/mongodb.conf", "--smallfiles"]

