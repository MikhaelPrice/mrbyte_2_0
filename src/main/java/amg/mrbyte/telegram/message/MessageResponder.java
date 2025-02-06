package amg.mrbyte.telegram.message;

import amg.mrbyte.telegram.message.service.impl.TextMessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageResponder {

  default BotApiMethod<?> respondMessage(Message message) {
    if (message.hasText()) {
      return new TextMessageService(message).respond();
    }
    return null;
  }
}
