package org.example.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);


    private final RestTemplate restTemplate;
    private final String productServiceHost;
    private final int productServicePort;

    @Autowired
    public HomeController(
            RestTemplate restTemplate,
            @Value("${app.product-service.host}") String productServiceHost,
            @Value("${app.product-service.port}") int productServicePort) {
        this.productServiceHost = productServiceHost;
        this.productServicePort = productServicePort;
        this.restTemplate = restTemplate;
    }


    @GetMapping(value="/")
    public String genHome() {
        String reponse = restTemplate.getForObject("http://"+productServiceHost+":"+productServicePort+"/", String.class);
        return "<H1>Hello from the "+reponse+"</H1>";
    }
}
