#!/bin/bash

# Set JAVA_HOME manually
export JAVA_HOME="/opt/render/project/.jdk"

# Make mvnw executable
chmod +x mvnw

# Run the build
./mvnw clean package
