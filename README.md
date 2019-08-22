# The distribute-system
  ## It contains the following services:
  ###
 - server-service
 - zuul-service
 - turbine-service
 - order-service
 - product-service
 - gross-settlement-service

# Requirements
 ###
- Maven - 3.x.x
- Java - 1.8.x
 ##

# Steps to Setup
 ###
 1.  Clone the application 
`git clone https://gitee.com/jkyec/distribute-system.git`
 2. Build and run the app
    
    - Method 1
      - Start each service in turn
    ```
    1.run server-service
      cd server-service
      mvn clean package
      java -jar target/server-service-0.0.1-SNAPSHOT.jar
    2.run turbine-service
      cd turbine-service
      mvn clean package
      java -jar target/turbine-service-0.0.1-SNAPSHOT.jar
    3.run zuul-service
      cd zuul-service
      mvn clean package
      java -jar target/zuul-service-0.0.1-SNAPSHOT.jar
    4.run product-service
      cd product-service
      mvn clean package
      java -jar target/product-service-0.0.1-SNAPSHOT.jar
    5.run order-service
      cd order-service
      mvn clean package
      java -jar target/order-service-0.0.1-SNAPSHOT.jar
    6.run gross-settlement-service
      cd gross-settlement-service
      mvn clean package
      java -jar target/gross-settlement-service-0.0.1-SNAPSHOT.jar
    ```
        
    - Method 2
      - Start all services with a shell command
    ``` 
    ./app start
    ./app stop
    ./app restart
    ```
 3. server page `http://localhost:8761/`
 4. hystrix page `http://localhost:12086/hystrix`
 5. turbine dashboard page `http://localhost:12086/hystrix/monitor?stream=localhost%3A12086%2Fturbine.stream`
 ![turbine-dashboard](turbine-dashboard.jpeg 'turbine-dashboard')
 6. swagger2 ui
  ![swagger2 ui](swagger2_ui.jpeg 'swagger2 ui')
 7. config.
 
    `You can invoke the refresh Actuator endpoint by sending an empty HTTP POST to the client’s refresh endpoint, 
 http://localhost:8888/actuator/refresh`