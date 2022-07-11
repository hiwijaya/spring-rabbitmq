package com.hiwijaya.springrabbitmq.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@ToString
public class Order implements Serializable{

    private String id;
    private String name;
    private Integer quantity;
    private BigDecimal price;

}
