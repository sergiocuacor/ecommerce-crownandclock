#!/bin/bash
set -e

echo "Waiting for MySQL to be ready..."
sleep 10

echo "Starting application..."
exec java -jar /app/app.jar