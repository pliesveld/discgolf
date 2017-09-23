FROM mongo:latest

COPY scripts/heartbeat.sh /usr/local/bin/heartbeat

COPY init.d/ /docker-entrypoint-initdb.d/

RUN bash -c 'mkdir -p /opt/mongo_data'

ENV MONGO_INITDB_DATA /opt/mongo_data

ENV MONGO_INITDB_DATABASE discgolf

COPY data/ /opt/mongo_data/

CMD ["mongod"]

