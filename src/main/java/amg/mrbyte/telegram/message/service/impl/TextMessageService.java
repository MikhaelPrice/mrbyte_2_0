package amg.mrbyte.telegram.message.service.impl;

import amg.mrbyte.telegram.message.MessageTypes;
import amg.mrbyte.telegram.message.service.IMessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public record TextMessageService(Message message) implements IMessageService {

  @Override
  public MessageTypes analise(Message message) {
    return null;
  }

  @Override
  public BotApiMethod<?> respond() {
    return null;
  }
}
