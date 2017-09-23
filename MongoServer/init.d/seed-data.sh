#!/bin/bash

mongoimport -d discgolf -c player $MONGO_INITDB_DATA/players.json
mongoimport -d discgolf -c course $MONGO_INITDB_DATA/courses.json

