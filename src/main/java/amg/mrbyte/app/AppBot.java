package amg.mrbyte.app;

import amg.mrbyte.config.BotConfig;
import amg.mrbyte.handlers.MessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class AppBot extends TelegramLongPollingBot implements MessageHandler {

  private final BotConfig botConfig;

  public AppBot(String token, BotConfig botConfig) {
    super(token);
    this.botConfig = botConfig;
  }

  @Override
  public String getBotUsername() {
    return botConfig.getName();
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage()) {
      BotApiMethod<?> botResponse = handleMessage(update.getMessage());
      try {
        execute(botResponse);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }
}
