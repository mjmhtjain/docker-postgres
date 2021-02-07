#!/bin/bash

AZ_RESOURCE_GROUP=rgb
AZ_DATABASE_NAME=rgb-postgres
AZ_LOCATION=eastus
AZ_POSTGRESQL_USERNAME=spring ##do not use
AZ_POSTGRESQL_PASSWORD=spring ##do not use
AZ_LOCAL_IP_ADDRESS=$(curl http://whatismyip.akamai.com/)

# Create a resource group.
az group delete \
    --name $AZ_RESOURCE_GROUP \
    --yes