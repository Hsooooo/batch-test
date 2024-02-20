#!/bin/bash
APP_NAME="factsheet-admin-api"

# move to project folder
cd ~/projects/${APP_NAME}
git reset
git checkout .
git clean -fdx
git pull
# execute docker build
/bin/bash build-docker.sh