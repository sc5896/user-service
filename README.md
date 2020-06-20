# microservices-springboot-kubernetes
This is a microservice architecture based project developed in springboot with mysql db. It contains three microservices:

User-Service: 
              A user management service which supports two rest end points currently (creata a new user and get user details).
              User data is persisted in MySql db.
              
              
Order-Service:
            An order management service which supports one rest end point only to get a list of all orders of a user(hard coded values).
            Currently no db is used.
            
            
Aggregaot-Service:
            An aggregator service to aggregate user details and all the orders details of a user. Curretly, has one rest end pint to
            get user details and order details of the user. Internally calls User-Service and Order-Service.
            
            
            
Deployment Strategy:
            The project is designed for deployment in the public/private cloud based environment with docker containers. 
            Each microservice has dockerfile with it which can used to build docker images. 
            The project can be deployed on any Kubernetes cluster and all the necessary kubernetes manifest files are included 
            in the source code.
            
            
            
