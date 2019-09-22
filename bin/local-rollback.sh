#!/usr/bin/env bash
###
# bash ./bin/local-rollback.sh
###
ARGS=${1:-}
ROOT_PROJECT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)
cd "${ROOT_PROJECT_DIR}" && ./mvnw release:rollback -s .mvn/settings.xml
git fetch --all -p -a --tags
#git tag -l
#git tag -d $latestTag
