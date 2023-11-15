package com.example.testspringapp.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Getter
@Component
@RequiredArgsConstructor
public class BotConfig extends TelegramLongPollingBot {

    private final BotHandler botHandler;
    @Value("${telegram-bot-name}")
    private String botUsername;

    @Value("${telegram-bot-token}")
    private String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        try {
            var messageText = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();

            var response = botHandler.onMessage(chatId, messageText);

            var message = new SendMessage();
            message.setChatId(chatId);
            message.setText(response);
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

}
