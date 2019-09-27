package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer id;
    private Integer pid;
    private Integer num;
    private BigDecimal money;
    private Goods goods;
}
