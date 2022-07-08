package com.hiwijaya.springrabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private String id;
    private String name;
    private Integer quantity;
    private BigDecimal price;

}
