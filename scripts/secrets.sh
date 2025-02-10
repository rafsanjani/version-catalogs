#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

cat <<EOF >> ~/.gradle/gradle.properties

mavenCentralUsername=$MAVEN_CENTRAL_USERNAME
mavenCentralPassword=$MAVEN_CENTRAL_PASSWORD
EOF

# Confirm generated secrets
echo "Generated secrets.properties:"
cat ~/.gradle/gradle.properties
