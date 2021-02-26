Consul container
```
$ docker run -d --name consul-1 -p 8500:8500 -e CONSUL_BIND_INTERFACE=eth0 consul
```

Start all containers
```
$ docker-composer up -d
```

1) Start gateway-service
2) Start profile-service


##Building Java Projects with Maven
https://spring.io/guides/gs/maven/

##Spring Boot with Docker
https://spring.io/guides/gs/spring-boot-docker/


docker build -t stu1901727007/uniplovdiv:profile --build-arg JAR_FILE=profile-service/target/\*.jar .

docker build -t stu1901727007/uniplovdiv:gateway --build-arg JAR_FILE=gateway-service/target/\*.jar --build-arg SERVER_PORT=8181 .
docker run -p 8181:8181 stu1901727007/uniplovdiv:gateway

