FROM node:10.22.0-buster

COPY . /opt
WORKDIR /opt
RUN npm install
RUN npm run-script build
RUN npm install -g serve

EXPOSE 5000 
CMD serve build