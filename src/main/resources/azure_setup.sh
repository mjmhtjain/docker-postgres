#!/bin/bash

AZ_RESOURCE_GROUP=rgb
AZ_DATABASE_NAME=rgb-postgres
AZ_LOCATION=eastus
AZ_POSTGRESQL_USERNAME=spring ##do not use
AZ_POSTGRESQL_PASSWORD=spring ##do not use
AZ_LOCAL_IP_ADDRESS=49.36.134.72

# Create a resource group.
az group create \
    --name $AZ_RESOURCE_GROUP \
    --location $AZ_LOCATION \
    | jq

# Create a postgres server
az postgres server create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name $AZ_DATABASE_NAME \
    --location $AZ_LOCATION \
    --sku-name B_Gen5_2 \
    --storage-size 5120 \
    | jq

az postgres server firewall-rule create \
    --resource-group $AZ_RESOURCE_GROUP \
    --name $AZ_DATABASE_NAME-database-allow-local-ip \
    --server $AZ_DATABASE_NAME \
    --start-ip-address $AZ_LOCAL_IP_ADDRESS \
    --end-ip-address $AZ_LOCAL_IP_ADDRESS \
    | jq