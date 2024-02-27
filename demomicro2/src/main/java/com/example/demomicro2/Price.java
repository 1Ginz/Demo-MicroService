package com.example.demomicro2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class    Price {
    private Long id;

    private Long price;

    private int discount;

    public void setDiscount(int discount) {
        this.discount = discount;
        this.price = this.price * this.discount / 100;
    }
}
