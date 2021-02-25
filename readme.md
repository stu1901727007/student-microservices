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


docker build -t stu1901727007/uniplovdiv:profile .

docker run -p 8383:8383 stu1901727007/uniplovdiv:gateway