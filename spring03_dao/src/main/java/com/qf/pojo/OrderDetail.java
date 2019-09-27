package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private Integer id;
    private String oid;
    private Integer pid;
    private Integer num;
    private BigDecimal money;
    private Goods goods;
}
