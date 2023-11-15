package com.example.testspringapp.cryptocurrencypull.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cryptocurrencyfetcher", url = "https://api.mexc.com/api/v3/ticker/price")
public interface CryptocurrencyFetcher {

    @RequestMapping(method = RequestMethod.GET)
    String fetchLastUpdates();

}
