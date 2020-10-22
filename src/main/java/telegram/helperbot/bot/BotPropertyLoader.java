package telegram.helperbot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BotPropertyLoader {

    private static final Logger logger = LoggerFactory.getLogger(BotPropertyLoader.class);

    private static final Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/bot.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            logger.error("Properties loading error", e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }

}
