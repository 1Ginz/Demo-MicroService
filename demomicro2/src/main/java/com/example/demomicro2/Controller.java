package com.example.demomicro2;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/price")
@RestController
public class Controller {


    @Value("${server.port}")
    private String port;

    private List<Price> priceList = new ArrayList<>();

    private void init(){
        this.priceList.add(new Price(1L,1000L,1));
        this.priceList.add(new Price(2L,2000L,1));
        this.priceList.add(new Price(3L,3000L,1));
        this.priceList.add(new Price(4L,4000L,1));
        this.priceList.add(new Price(5L,5000L,1));
    }

    @GetMapping("/port")
    public String getPort(){
        return this.port;
    }

    @GetMapping("/{productId}")
    public Price getPrice(@PathVariable Long productId){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        init();
        Price price = null;
        for (Price p : priceList){
            if (productId.equals(p.getId())){
                return p;
            }
        }
        return null;
//        return Mono.just(price);
    }

}
