package amg.mrbyte.handlers;

import amg.mrbyte.service.telegram.IMessageService;
import amg.mrbyte.service.telegram.impl.TextMessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHandler {

  default BotApiMethod<?> handleMessage(Message message) {
    IMessageService messageService;
    if (message.hasText()) {
      messageService = new TextMessageService();
      return messageService.respondOnMessage(message);
    }
    return null;
  }
}
