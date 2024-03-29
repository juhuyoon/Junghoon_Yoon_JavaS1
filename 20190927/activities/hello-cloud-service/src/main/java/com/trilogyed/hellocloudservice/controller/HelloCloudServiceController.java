package com.trilogyed.hellocloudservice.controller;

import com.trilogyed.hellocloudservice.util.feign.RandomGreetingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloCloudServiceController {

    //This would be through the RestTemplate
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    private RestTemplate restTemplate = new RestTemplate();
//
//    @Value("${randomGreetingServiceName}")
//    private String randomGreetingServiceName;
//
//    @Value("${serviceProtocol}")
//    private String serviceProtocol;
//
//    @Value("${servicePath}")
//    private String servicePath;
//
//    @Value("${officialGreeting}")
//    private String officialGreeting;

    @Autowired
    private final RandomGreetingClient client;

    public HelloCloudServiceController(RandomGreetingClient client) {
        this.client = client;
    }

    @GetMapping(value ="/hello")
    public String helloCloud() {
        //With RestTemplate
//        List<ServiceInstance> instances = discoveryClient.getInstances(randomGreetingServiceName);
//
//        String randomGreetingServiceUri = serviceProtocol + instances.get(0).getHost() + ":" + instances.get(0).getPort() + servicePath;
//
//        String greeting = restTemplate.getForObject(randomGreetingServiceUri, String.class);
//
//        return greeting;
        return client.getRandomGreeting();
    }


}
