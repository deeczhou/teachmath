#! /bin/bash

docker build . -t dczhou/homeworkserver:server
docker push dczhou/homeworkserver:server