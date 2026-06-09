#!/bin/bash
set -e

echo "Deploying latest changes..."

# Pull latest code
git pull origin main

# Rebuild and start containers
docker-compose up --build -d

echo "Deployment completed successfully!"
