package com.company.hellocloudservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RefreshScope
@ResponseStatus
public class HelloCloudServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    //Setting the official Greeting as the value

    @Value("${randomGreetingServiceName}")
    private String randomGreetingServiceName;

    @Value("${serviceProtocol}")
    private String serviceProtocol;

    @Value("${servicePath}")
    private String servicePath;

    @Value("${officialGreeting}")
    private String officialGreeting;

    @GetMapping(value="/hello")
    public String helloCloud() {
        List<ServiceInstance> instances = discoveryClient.getInstances(randomGreetingServiceName);

        String randomGreetingServiceUri = serviceProtocol + instances.get(0).getHost() + ":" + instances.get(0).getPort() + servicePath;

        String greeting = restTemplate.getForObject(randomGreetingServiceUri, String.class);

        return greeting;
    }


}
