package com.qf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String id;
    private Integer uid;
    private BigDecimal money;
    private String status;
    private Date time;
    private Integer aid;
    private Address address;
    private List<OrderDetail> orderDetails;

}
