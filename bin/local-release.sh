#!/usr/bin/env bash
###
# bash ./bin/local-release.sh
###
ARGS=${1:-}
ROOT_PROJECT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)
cd "${ROOT_PROJECT_DIR}" && ./mvnw -DskipTests -B -s .mvn/settings.xml \
  -Dresume=false release:prepare release:perform \
  -Plocal-release ${ARGS}
#cd "${ROOT_PROJECT_DIR}" && ./mvnw release:rollback -s .mvn/settings.xml
