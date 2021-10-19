# springboot-mongodb-k8s-parth
Brief Introduction
I have created REST API using Springboot and Spring cloud which performs Get,Post,Update,Delete operations, where data is saved into mongo db.
Both Spring boot and Mongo db are hosted in kubernetes env(Google Cloud Provider) where Springboot is expose via LoadBalancer service to internet and Mongo is expose via Cluster IP service.
Lets understand all moving parts of code and deployment in brief.
ClientController.java – It has code which performs GET,POST,UPDATE,DELETE.

