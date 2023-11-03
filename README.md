
## 🛠 SOAP Launch Steps
- Open with eclipse photon JEE
- Deploying the microservice on Tomcat

#### lien microservice : localhost:8080/services/TrainService

## 🛠 REST Launch Steps

- Open with an IDE and run main 

#### Java version > 17
- Use ebean --> intellij : add ebean ehancer plugin

#### maven : 
- enhance ebean model files (codegen ebean generation): 

```bash
   mvn clean enhance:enhance
```

#### build: 
```bash
   mvn clean package
```

PS : If error due to database then change database access to "localhost" and create database using db-create-all.sql in rest-train-master. 
