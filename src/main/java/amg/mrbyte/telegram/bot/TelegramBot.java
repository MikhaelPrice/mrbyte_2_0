package amg.mrbyte.telegram.bot;

import amg.mrbyte.config.BotConfig;
import amg.mrbyte.telegram.message.MessageResponder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot implements MessageResponder {

  private final BotConfig botConfig;

  public TelegramBot(String botToken, BotConfig botConfig) {
    super(botToken);
    this.botConfig = botConfig;
  }

  @Override
  public String getBotUsername() {
    return botConfig.getBotName();
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage()) {
      BotApiMethod<?> respondMessage = respondMessage(update.getMessage());
      try {
        execute(respondMessage);
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }
}
