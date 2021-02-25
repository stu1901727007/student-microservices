Consul container
```
$ docker run -d --name consul-1 -p 8500:8500 -e CONSUL_BIND_INTERFACE=eth0 consul
```

Start all containser
```
$ docker-composer up -d
```

1) Start gateway-service
2) Start profile-service
