package com.example.testspringapp.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CryptocurrencyRecord {

    private Long symbol;
    private BigDecimal actualPrice;
}
