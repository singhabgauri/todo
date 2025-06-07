#!/bin/bash
export TERM=dumb
export NO_COLOR=true
mvn clean package -B -DskipTests --no-transfer-progress