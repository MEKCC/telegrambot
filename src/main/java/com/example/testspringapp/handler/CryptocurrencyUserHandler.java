package com.example.testspringapp.handler;

import com.example.testspringapp.cryptocurrencypull.feign.CryptocurrencyFetcher;
import com.example.testspringapp.model.CryptocurrencyRecord;
import com.example.testspringapp.model.User;
import com.example.testspringapp.repo.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CryptocurrencyUserHandler {

    private final CryptocurrencyFetcher cryptocurrencyFetcher;
    private final UserRepo userRepo;

    public boolean updatePrices(Long userId) {
        try {
            var newPrices = cryptocurrencyFetcher.fetchLastUpdates();
            var user = new User();
            user.setId(userId);
            user.setCryptocurrencyRecords(newPrices);
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    Working on it, should be done cryptocurrencyRecords field as new Model and parse data there, didn't have time for it
    public boolean checkPricesUpdates() throws JsonProcessingException {
//        var newPrices = cryptocurrencyFetcher.fetchLastUpdates();
//        var users = userRepo.findAll();
//        for (User user : users) {
//            var userRecords = user.getCryptocurrencyRecords();
//            var cryptocurrencyRecords = new ObjectMapper().readValue(userRecords, new TypeReference<List<CryptocurrencyRecord>>() {
//            });
//            var newPricesRecords = new ObjectMapper().readValue(newPrices, new TypeReference<List<CryptocurrencyRecord>>() {
//            });
//            for (CryptocurrencyRecord cryptocurrencyRecord : cryptocurrencyRecords) {
//                if (cryptocurrencyRecord.getActualPrice() < newPricesRecords.get(cryptocurrencyRecord.getSymbol())) {
//
//                }
//            }
//        }
        return true;
    }
}
