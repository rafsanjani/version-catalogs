#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

# Generate Android and shared secrets
echo "Generating Android and shared secrets..."
cat <<EOF >> ../gradle.properties

mavenCentralUsername=$MAVEN_CENTRAL_USERNAME
mavenCentralPassword=$MAVEN_CENTRAL_PASSWORD
EOF

# Confirm generated secrets
echo "Generated secrets.properties:"
cat ../gradle.properties
