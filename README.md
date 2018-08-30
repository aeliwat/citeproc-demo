# citeproc-demo

### Technology :

 - *Spring Boot v2* : for quick build web application 
 - *Spring Cloud* : for connect the web app to cloud pivotal web services  to run simple host-demo
 - *CSL* : for create itemData provider & json Support (Serialized fields Name , convert to Json object) 
 
 ### FrontEnd API : 

 - *cite-proc.js* : CSL processor for Citation Style Language (CSL) stylesheets 
 - *main.js* : for handle Ajax request ( get Publication Meta Data , read CSL File, etc.)
 
  #### Deployed App :
 http://citeproc-cloud.cfapps.io/cite/
 
 #### Building Project locally
     $ git clone https://github.com/aeliwat/citeproc-demo.git
     $ cd citeproc-demo
     $ mvn clean install
 
 #### Run Project locally  
    $ mvn spring-boot:run   
    
##### Access deployed application http://localhost:7070/cite/
     

    
