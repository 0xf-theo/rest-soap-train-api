
# ✏️ Soap-Rest API



## Authors

- V. Théo
- C. Noa
- L. Aymeric
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

## ➕ Self-evaluation

### Requierments Marks (30)

| # | Requierments                                                        | Marks | Self-evaluation |
|---|---------------------------------------------------------------------|-------|-----------------|
| 1 | Create REST Train Filtering service B                                | 6     |    5 ✅              |
| 2 | Create SOAP Train Booking service A                                  | 4     |           3 ✅       |
| 3 | Interaction between two services                                    | 4     |       3 ✅           |
| 4 | Test with Web service Client (instead of using Eclipse's Web service Explorer) | 2 |           ❌       |
| 5 | Work with complex data type (class, table, etc.)                    | 2     |  2 ✅               |
| 6 | Work with database (in text file, xml, in mysql, etc.)              | 2     |       2 ✅          |
| 7 | Postman                                                             | 2     |         2 ✅        |
| 8 | OpenAPI                                                             | 3     |          3   ✅       |
| 9 | BPMS                                                                | 5     |       ❌          |
 

