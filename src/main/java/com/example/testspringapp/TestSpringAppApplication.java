package com.example.testspringapp;

import com.example.testspringapp.config.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@EnableFeignClients
public class TestSpringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringAppApplication.class, args);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(BotConfig botConfig) throws TelegramApiException {
        var botsApi = new TelegramBotsApi(DefaultBotSession.class);

        // Register our bot
        try {
            botsApi.registerBot(botConfig);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return botsApi;
    }

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void yourScheduledMethod() {
        System.out.println("Scheduled task is running...");
    }
}
