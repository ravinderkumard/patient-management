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
