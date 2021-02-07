./mvnw clean package -DskipTests

./mvnw spring-boot:run

docker-compose -f docker-compose-azure.yml up

docker-compose -f docker-compose-azure.yml down
