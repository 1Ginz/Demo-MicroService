package com.example.demomicro;


import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RequestMapping("/product")
@RestController
@RefreshScope
public class Controller {


    private Logger logger = LoggerFactory.getLogger(Controller.class);


    @Autowired
    private EurekaClient eurekaClient;


    @Autowired
    private PriceClient priceClient;

    private final RestTemplate restTemplate;

    public WebClient webClient = WebClient.create();

    private ProductService productService;

    @Value("${message}")
    private String message;


    Controller(RestTemplate restTemplate) {

        productService = new ProductService();
        this.restTemplate = restTemplate;
    }

    @GetMapping("/message")
    public String getMessage(){
        return this.message;
    }


    @GetMapping("/{id}")
    Mono<Product> getMonoProduct(@PathVariable Long id) {
        Mono<Product> product = Mono.just(this.productService.getById(id));

//        Price price = restTemplate.getForObject("http://localhost:8889/api/price/"+id,Price.class);
        Mono<Price> price = webClient.get().uri("http://localhost:8889/api/price/{id}", id).retrieve().bodyToMono(Price.class);

//        return ResponseEntity.ok().body(new Product(product.getId(),product.getName(),product.getDetail(),price.getPrice().intValue()));
        return Mono.zip(product, price).map(tuple2 -> new Product(tuple2.getT1().getId(), tuple2.getT1().getName(), tuple2.getT1().getDetail(), tuple2.getT2().getPrice().intValue()));
    }

    @GetMapping("/detail/{id}")
    @HystrixCommand(fallbackMethod ="fallbackMethod")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
    public Product getProduct(@PathVariable Long id) {

        this.logger.error("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC"+eurekaClient.getApplications().getRegisteredApplications().toString());

        Product product = this.productService.getById(id);

//        Price price = this.priceClient.getPrice(id);
        Price price = restTemplate.getForObject("http://localhost:8889/price/"+id,Price.class);
        return new Product(product.getId(),product.getName(),product.getDetail(),price.getPrice().intValue());
    }

    public Product fallbackMethod(@PathVariable Long id){
        logger.error("thread blocking");
        return new Product();
    }

    @GetMapping("/price-service/port")
    public String getPricePort(){
        return this.priceClient.getPort();
    }





}
