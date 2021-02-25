Consul container
```
$ docker run -d --name consul-1 -p 8500:8500 -e CONSUL_BIND_INTERFACE=eth0 consul
```

Start all containser
```
$ docker-composer up -d
```


####Gateway-service
http://localhost:8181/

routes:
    
    - id: profile-service
          uri: lb://profile-service
          predicates:
            - Path=/profile/**
          filters:
            - RewritePath=/profile/(?<path>.*), /$\{path}
    - id: billing-service
          uri: lb://billing-service
          predicates:
    - Path=/billing/**
          filters:
            - RewritePath=/billing/(?<path>.*), /$\{path}


####Profile service
http://localhost:RANDOM/
