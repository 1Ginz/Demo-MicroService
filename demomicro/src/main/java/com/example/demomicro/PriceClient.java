package com.example.demomicro;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

//@FeignClient(value = "priceclient", url = "http://localhost:8889/api/price")
@FeignClient(name = "gateway-service")
public interface PriceClient {

    @GetMapping("/price/{productId}")
    Price getPrice(@PathVariable("productId") Long productId);

    @GetMapping("/price/port")
    public String getPort();

}
