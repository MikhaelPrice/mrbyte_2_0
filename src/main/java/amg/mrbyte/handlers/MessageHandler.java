package amg.mrbyte.handlers;

import amg.mrbyte.service.telegram.impl.TextMessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHandler {

  default BotApiMethod<?> handleMessage(Message message) {
    if (message.hasText()) {
      return new TextMessageService().respond(message);
    }
    return null;
  }
}
