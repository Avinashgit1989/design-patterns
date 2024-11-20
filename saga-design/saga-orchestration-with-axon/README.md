# SAGA design-patterns with Axon CQRS
## What is SAGA design Pattern?
    The SAGA desugn patter is the way to manage data consistancy acros microservices in distributed transaction scenario.

## What is CQRS?
    CQRS stands for Command Query Responsibility Segregation, which is a design pattern used in software engineering to separate the responsibilities of handling commands (changing state) from the responsibility of querying data.

In this implementation i have created a e-commerce product service which will create product and 
then user will order for that particular product using order API.Once the order will be created then we will validate the user payment and once payment is done then shipment
service will add the shipment details and order will be approved.

