# Patient - Management
    Microservice based Patient management System.
    This is being build to demostrate various different technologies and stacks and bring them together to make a working system.
    
### Services Developed
-   Patient Service
-   Billing Service

Patient service is client facing application that allow user to make CRUD request to create/update/delete a patient from patient DB. 
Patient Service will make a gRPC call to Billing Service.
Billing Service is a server side application that will handle all the billing related operations.

### Technologies Used
-   Java
-   Spring Boot
-   gRPC
-   Docker
-   PostgreSQL


### gRPC vs REST
gRPC is a high-performance, open-source universal RPC framework that can run in any environment.
It is designed to make it easier to build distributed systems and microservices. gRPC uses HTTP/2 for transport, Protocol Buffers as the interface description language, and it provides features such as
authentication, load balancing, and more.
gRPC is a good choice for building microservices because it is efficient, supports multiple programming languages, and provides a lot of features out of the box.
It is particularly well-suited for applications that require high performance and low latency, such as real-time applications, streaming applications, and applications that require a lot of data to be transferred between services.  
REST, on the other hand, is a more traditional approach to building web services. It is based on the HTTP protocol and uses standard HTTP methods such as GET, POST, PUT, and DELETE to perform operations on resources. REST is widely used and supported by many programming languages and frameworks.
It is a good choice for building web services that are simple, easy to understand, and easy to use. REST is particularly well-suited for applications that require a lot of data to be transferred between services, such as web applications and mobile applications.  

### gRPC vs Kafka
gRPC and Kafka are both technologies that can be used to build distributed systems and microservices, but they serve different purposes and have different strengths and weaknesses.
gRPC is a high-performance, open-source universal RPC framework that is designed to make it easier to build distributed systems and microservices. It uses HTTP/2 for transport, Protocol Buffers as the interface description language, and it provides features such as authentication, load balancing, and more. gRPC is a good choice for building microservices because it is efficient, supports multiple programming languages, and provides a lot of features out of the box. It is particularly well-suited for applications that require high performance and low latency, such as real-time applications, streaming applications, and applications that require a lot of data to be transferred between services.
Kafka, on the other hand, is a distributed streaming platform that is designed to handle large volumes of data in real-time. It is used to build real-time data pipelines and streaming applications. Kafka is a good choice for building applications that require high throughput, low latency, and fault tolerance. It is particularly well-suited for applications that require real-time data processing, such as log aggregation, event streaming, and real-time analytics.
In summary, gRPC is a good choice for building microservices that require high performance and low latency, while Kafka is a good choice for building real-time data pipelines and streaming applications. They can be used together in a distributed system, with gRPC handling the communication between services and Kafka handling the real-time data processing.   

### How to run the project
1. Clone the repository
   ```bash
   git clone   
2. Navigate to the project directory
   ```bash
   cd patient-management
   ```
3. Build the project using Maven
   ```bash
   mvn clean install
   ```
4. Start the PostgreSQL database using Docker or local setup
    ```bash
    docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
    ``` 
5. Start the Patient Service
   ```bash
   cd patient-service
   mvn spring-boot:run
   ```
6. Start the Billing Service
   ```bash
   cd billing-service
   mvn spring-boot:run
   ```
7. Access the Patient Service at `http://localhost:8080/patients`
   You can use tools like Postman or cURL to make requests to the Patient Service.  
### API Endpoints
-   **Create Patient**: `POST /patients`
-   **Get Patient**: `GET /patients/{id}`
-   **Update Patient**: `PUT /patients/{id}`
-   **Delete Patient**: `DELETE /patients/{id}`
-   **Get All Patients**: `GET /patients`
-   **Billing Service**: gRPC calls to handle billing operations


### Using DockerFile
You can also run the services using Docker. The project includes Dockerfiles for both the Patient Service and Billing Service. To build and run the Docker containers, follow these steps:
1. Navigate to the project directory
   ```bash
   cd patient-management
   ```
2. Build the Docker images
   ```bash
   docker build -t patient-service ./patient-service
   docker build -t billing-service ./billing-service
   ```
3. Run the Docker containers
   ```bash
   docker run -d -p 4000:4000 --name patient-service patient-service
   docker run -d -p 5000:5000 -p 5500:5500 --name billing-service billing-service
   ```
4. Access the Patient Service at `http://localhost:4000/patients`
   You can use tools like Postman or cURL to make requests to the Patient Service.
### Conclusion
This project demonstrates how to build a microservice-based patient management system using Java, Spring Boot, gRPC, Docker, and PostgreSQL. It showcases the use of gRPC for efficient communication between services and provides a simple API for managing patients. The project can be extended to include more features and services as needed.


### Kafka Broker Dockerfile
This Dockerfile sets up a Kafka broker using the Bitnami Kafka image. It configures the broker to connect to a Zookeeper instance and allows plaintext communication. The environment variables can be adjusted as needed for your specific setup.

### Starting Kafka Broker with Docker Compose
To start the Kafka broker using Docker Compose, you can create a `docker-compose.yml` file in the `kafka-broker` directory with the following content:
    ```bash
    docker-compose up
    ```
    
## Run Kafka CLI inside the container to check or create a topic:
### Create a topic
    ```bash
    docker exec kafka kafka-topics.sh --create --topic patient-management-topic --bootstrap-server localhost:9092 --partitions 1 
    --replication-factor 1
```
### List topics
    ```bash
    docker exec kafka kafka-topics.sh --list --bootstrap-server localhost:9092
    ```
### Produce messages to the topic
    ```bash
    docker exec -it kafka kafka-console-producer.sh --topic patient-management-topic --bootstrap-server localhost:9092
    ```

### Consume messages from the topic
    ```bash
    docker exec -it kafka kafka-console-consumer.sh --topic patient-management-topic --from-beginning --bootstrap-server localhost:9092
    ```

