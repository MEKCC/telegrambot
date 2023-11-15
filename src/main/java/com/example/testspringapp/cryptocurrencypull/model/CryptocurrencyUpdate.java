package com.example.testspringapp.cryptocurrencypull.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CryptocurrencyUpdate {

    private String symbol;
    private BigDecimal price;
}
