# springboot-mongodb-k8s-parth
Brief Introduction</br>
I have created REST API using Springboot and Spring cloud which performs CRUD operations (GET,POST,UPDATE,DELETE) , where data is saved into mongo db.
Both Spring boot and Mongo db are hosted in kubernetes env(GCP-Google Cloud Provider) where Springboot is expose via LoadBalancer service to internet and Mongo is expose via Cluster IP service where spring boot app will connect to mongo db via mong-service.</br>
**Lets understand all moving parts of code and deployment in brief.**</br>

**ClientController.java** – It has code which performs GET,POST,UPDATE,DELETE. </br>
**Client.java**- It is the Client document schema which we will store in mongo db.
private Long id; </br>
private String clientName;</br>
private String clientType;</br>
private Integer clientID;</br>
private Integer gfcid;</br>
private String clientRegion;</br>
**ClientRepo.java** – It is repo which performs CRUD operations in mongo db.</br>
**application.yml**- It has db connection information where all db details are fetch from kubernetes configmap and secretes.Pls make sure to set default mongo db port-27017.</br> </br>
Now once code is ready then login to GCP , follow below steps to do deployment up and running in GCP Kubernetes env,</br></br>
a) **Login to GCP(Google Cloud Provider)**,go to Kubernetes Engine- Cluster-->Create New Cluster--> Connect (make sure kubernetes in up ,run command -kubectl get nodes to check)</br>
b) **Clone the git code** using cmd-</br>&nbsp;&nbsp; git clone https://github.com/parthec2/springboot-mongodb-k8s-parth.git  , it will download whole code in kube cluster</br>
c) Run - Cd springbootmongodb-k8s-parth, then **Build code using cmd**-</br>&nbsp;&nbsp;  mvn install -DskipTests </br>
b) Once jar is created, then **create image using DockerFile by command** -</br>&nbsp;&nbsp; Docker build –t parthec2/spring-mongo-service</br>
c) **Push jar to docker hub by cmd**-</br>&nbsp;&nbsp; docker push ( Login to docker hub, it should have push image to your docker hub)</br>
d) Now run - cd src/main/resources and  **create secrets and configmap** by using below commands
  </br>&nbsp;&nbsp;&nbsp; kubectl create -f mongo-secret.yml
  </br>&nbsp;&nbsp;&nbsp;&nbsp; kubectl create -f mongo-config.yml
e) **Create mongo db persistent volumen and claim** using below commands:
  </br>&nbsp;&nbsp;&nbsp; kubectl create -f mongo-pv.yaml</br>
  </br>&nbsp;&nbsp;&nbsp; kubectl create -f mongo-pvc.yaml</br>
g)**Create mongo db deployment(pods) and service(type-ClusterIP)** using below commands:
  </br>&nbsp;&nbsp;&nbsp; kubectl create -f mongo-deployment.yaml (mongo db user name and pwd are set using env variables which are read from secrets)</br>
  </br>&nbsp;&nbsp;&nbsp; kubectl create -f mongo-service.yml</br>
  **Check mongo pods health using beow command**- 
  </br>&nbsp;&nbsp;&nbsp; kubectl exec -it pod/<mongo-pod-name> -- /bin/sh </br>
  </br>&nbsp;&nbsp;&nbsp;&nbsp; #mongo -u test -p test@123</br>
  </br>&nbsp;&nbsp;&nbsp;&nbsp; #show dbs</br>
  </br>&nbsp;&nbsp;&nbsp;&nbsp; #use admin</br>
  </br>&nbsp;&nbsp;&nbsp;&nbsp; #show collections</br>
  </br>&nbsp;&nbsp;&nbsp;&nbsp; #db.client.find().pretty(); --It will show empty as we have not inserted any doc yet,</br></br>
  
i) **Create cluster role** otherwise springboot-service will give **Forbidden error(403)** which accessing mongo service</br>
#create rolebinding 
</br>&nbsp;&nbsp;&nbsp; kubectl create rolebinding default-view --clusterrole=view --serviceaccount=default:default --namespace=default</br></br>

j)**Now,create spring boot deployment(2 Pods) and service(type-LoadBalancer) using below commands:**
  </br>&nbsp;&nbsp;&nbsp; kubectl create -f deployment.yml (It create 4 env variables which will be used by application.yml file)</br></br>
  </br>&nbsp;&nbsp;&nbsp; Get External IP of springboot-service by using command
  </br>&nbsp;&nbsp;&nbsp;kubectl get svc/springboot-service
  </br>&nbsp;
  **HTTP GET**</br>&nbsp;&nbsp;&nbsp;
  The GET URL will be form as below- :</br>&nbsp;&nbsp;&nbsp;
  Hit  in Postman tool OR browser from your laptop/mobile device</br>&nbsp;&nbsp;&nbsp;
  http://34.68.140.183:8080/getAllClients
  
  It will return json as below if we have inserted data 
  </br>&nbsp;&nbsp;&nbsp;
  [
    {"id":1,"clientName":"ABC-BANK","clientType":"INTERNAL","clientID":44444,"gfcid":5000022,"clientRegion":"CCD"},
    {"id":2,"clientName":"XYZ-BANK","clientType":"INTERNAL","clientID":555555,"gfcid":7222,"clientRegion":"ASD"}
  ]
  
  
**HTTP POST**
Go to Postman tool, select Post as option from drop down and add url- http://34.68.140.183:8080/addClient
Go to body--select raw-type-JSON

{
    "id": 4,
    "clientName":"Barclays",
    "clientType":"EXTERNA",
    "clientID": 232323,
    "gfcid": 12345,
    "clientRegion": "SSB"
}

Similary, you can try search by client id(getClient/{id}) and delete by client id (deleteClient/{id}).

Pls email me at parth.ec2@gmail.com for any queries/feedback/suggestions,,,,
Keep coding,,,Have a ncie day, :)


