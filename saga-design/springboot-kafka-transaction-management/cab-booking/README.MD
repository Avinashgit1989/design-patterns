## CAB BOOKING SERVICE MAINTAIN TRANSACTION WITH KAFKA 
This service is having multi module service which contains three services.
    
   - cab-common-service
   - cab-driver-service
   - cab-service

### cab-common-service
 This is used to contain all the common functionality for cab and driver services. it is being added as a module in both services.
 
### cab-driver-service
This service contains all API related to add new driver, get all driver, get Driver by Id.
When we call addNewDriver API it will add new driver with status PENDING and 
after adding a new driver this service will create one cab event and publish it to the 
kafka topic ADD_CAB_EVENT.

Now cab-service is having Kafka Listener which will consume the cab event that is published
by cab-driver-service and validate the driver by registration number. 
If cab driver data is existed in driver table with same registration then it will 
add the cab details in db with status FAILED. 
if it is not exist then it will add new cab details in cab table with status SUCCESS and
create one driver event to and published the event to topic UPDATE_DRIVER_STATUS_EVENT to
update the driver status as SUCCESS in driver table.

### cab-service
This service contains all API related to add new cab, get all cab, get Cab by Id, delete cab by ID.

### How to Test API
##### Perform the below steps to test the API

 ##### 1. Download the kafka and extract to the local system. Download as binary

    [URL To Download KAFKA](https://kafka.apache.org/downloads)

##### 2. Modify the zookeeper.properties to add log on local directory.

      dataDir=E:/kafka/zookeeper-data
   
##### 3. Modify the servers.properties.

        listeners=PLAINTEXT://localhost:9092
        auto.create.topic.enable=false
        log.dirs=E:/kafka/kafka-logs

##### 4. Start the zookeeper.

        E:\kafka > .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

##### 5. Start the kafka server

       E:\kafka > .\bin\windows\kafka-server-start.bat .\config\server.properties

##### 6. Start the cab-driver-service and use below swagger URl to assess EndPoints.

        [Cab-Driver-Service :: Swagger URL](http://localhost:8081/swagger-ui/index.html)

##### 7. Start the cab-service

       [Cab-service :: Swagger URL](http://localhost:8082/swagger-ui/index.html)

#####