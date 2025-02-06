package amg.mrbyte.telegram.message.service;

import amg.mrbyte.telegram.message.MessageTypes;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface IMessageService {

  MessageTypes analise(Message message);

  BotApiMethod<?> respond();
}
