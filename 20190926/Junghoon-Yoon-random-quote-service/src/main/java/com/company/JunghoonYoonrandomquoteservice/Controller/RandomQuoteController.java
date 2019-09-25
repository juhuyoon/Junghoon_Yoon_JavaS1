package com.company.JunghoonYoonrandomquoteservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RefreshScope
@ResponseStatus
public class RandomQuoteController {

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${magicEightServiceName}")
    private String magicEight;

    @Value("${serviceProtocol}")
    private String serviceProtocol;

    @Value("${servicePath}")
    private String servicePath;

    @Value("${randomQuotes}")
    private ArrayList randomQuotes;

    Random rand = new Random();

    @GetMapping(value="/quotes")
    @ResponseStatus(value= HttpStatus.OK)
    public Object getQuotes() {
        return randomQuotes.get(rand.nextInt(randomQuotes.size()));
    }

    @GetMapping(value="/answerme")
    @ResponseStatus(value=HttpStatus.OK)
    public String getMagicEight() {
        List<ServiceInstance> instances = discoveryClient.getInstances(magicEight);

        String magicEightServiceUri = serviceProtocol + instances.get(0).getHost() + ":" + instances.get(0).getPort() + servicePath;

        String answer = restTemplate.getForObject(magicEightServiceUri, String.class);

        return answer;
    }


}
