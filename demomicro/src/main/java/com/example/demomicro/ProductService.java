package com.example.demomicro;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class ProductService {

    private List<Product> productList = new ArrayList<>();

    private void initProductList(){
        productList.add(new Product(1L,"iphone 6","iphone ne th ngu",1000));
        productList.add(new Product(2L,"iphone 7","iphone ne th ngu",1000));
        productList.add(new Product(3L,"iphone 8","iphone ne th ngu",1000));
        productList.add(new Product(4L,"iphone X","iphone ne th ngu",1000));
        productList.add(new Product(5L,"iphone 11","iphone ne th ngu",1000));
    }

    public Product getById(long id){
        this.initProductList();
        return this.productList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

}
