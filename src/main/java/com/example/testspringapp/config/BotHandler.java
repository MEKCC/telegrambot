package com.example.testspringapp.config;

import com.example.testspringapp.handler.CryptocurrencyUserHandler;
import com.example.testspringapp.model.User;
import com.example.testspringapp.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.lang.Integer.parseInt;

@Service
@RequiredArgsConstructor
public class BotHandler {

    private final UserRepo userRepo;
    private final CryptocurrencyUserHandler cryptocurrencyUserHandler;
    @Value("${telegram-user-limit}")
    private Long userLimit;

    public String onMessage(Long chatId, String text) {
        if (text.contains("start")) {
            if (userRepo.count() + 1 > userLimit) {
                return "You exceeded limit of users, please try later!";
            }
            var user = new User();
            user.setId(chatId);
            userRepo.save(user);
            cryptocurrencyUserHandler.updatePrices(chatId);
            return """
                Welcome onboard!
                Please set percentage to subscribe onto cryptocurrencies updates.
                                
                *"Percentage" - if some cryptocurrency becomes more expensive or cheaper by more than N percent we
                will notify you!
                *Example: "/percent 10"
                *Only integer value may be accepted.
                """;
        }
        if (text.contains("percent")) {
            try {
                var percent = parseInt(text.split(" ")[1]);
                if (!userRepo.existsById(chatId)) {
                    return """
                        Sorry, looks your user is not registered.
                        Please, clear the history exit the bot and enter again.
                        """;
                }
                var user = new User();
                user.setId(chatId);
                user.setPercent(percent);
                userRepo.save(user);
                return """
                    Thank you, we registered your percent.
                    We will notify you once there are updates.
                    """;
            } catch (NumberFormatException e) {
                return """
                    Sorry, you put invalid value for percent
                    *Example: "/percent 10"
                    """;
            }
        }
        if (text.contains("restart")) {
            if (!userRepo.existsById(chatId)) {
                return """
                        Sorry, looks your user is not registered.
                        Please, clear the history exit the bot and enter again.
                        """;
            }
            cryptocurrencyUserHandler.updatePrices(chatId);
            return """
                You successfully updated your state of cryptocurrencies prices! 
                """;
        }
        return null;
    }
}
