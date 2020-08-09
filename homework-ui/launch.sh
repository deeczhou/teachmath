#! /bin/bash
npm run-script build
npm install -g serve
serve -s build