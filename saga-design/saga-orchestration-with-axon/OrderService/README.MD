
### To Download the AxonIQ server use the below link and follow the steps.
 - #### Step 1.
    
   Click on below URL of official AxonIq Server.
   https://www.axoniq.io/download
 - #### Step 2.
   Click on download and release option.
 - #### Step3.
   fill the form. Once you will complete form it will send link on email Id to download.
 - #### Steps 4.
    Go to email inbox and click on link.
 - #### Step 5.
    Go to download and unzip the Axon server.

#### Steps to start the Axon Server
 - #### Steps 1.
  Go to Axon server extracted directory and run the axonserver.jar. In ny case i have extracted it in D: directory.
  D:\AxonServer-2024.1.4

 - #### Step 2
    Run below command on command prompt to start the Axon server.
   D:\AxonServer-2024.1.4>java -jar axonserver.jar


### Add below two dependency to OrderService
      <!-- https://mvnrepository.com/artifact/org.axonframework/axon-spring-boot-starter -->
		<dependency>
			<groupId>org.axonframework</groupId>
			<artifactId>axon-spring-boot-starter</artifactId>
			<version>4.5.4</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
		<dependency>
			<groupId>com.google.guava</groupId>
			<artifactId>guava</artifactId>
			<version>31.0.1-jre</version>
		</dependency>