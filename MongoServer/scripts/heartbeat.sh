#!/bin/bash

ARGS=

if [ -n "$MONGO_INITDB_ROOT_USERNAME" ]; then
    ARGS="--authenticationDatabase admin -u $MONGO_INITDB_ROOT_USERNAME -p $MONGO_INITDB_ROOT_password"
fi

mongo $ARGS $MONGO_INITDB_DATABASE <<-EOJS
    db.stats();
EOJS

