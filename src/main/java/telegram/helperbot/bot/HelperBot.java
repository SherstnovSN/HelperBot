package telegram.helperbot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelperBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(HelperBot.class);

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        logger.info("Message received: " + message);
        if ("привет".equalsIgnoreCase(message)) {
            sendMsg(update.getMessage().getChatId().toString(), "Привет!");
        }
    }

    @Override
    public String getBotUsername() {
        return BotPropertyLoader.getProperties().getProperty("bot.username");
    }

    @Override
    public String getBotToken() {
        return BotPropertyLoader.getProperties().getProperty("bot.token");
    }

    public synchronized void sendMsg(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Sending message error", e);
        }
    }

}
