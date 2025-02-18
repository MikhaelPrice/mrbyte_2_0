package amg.mrbyte.service.telegram;

import amg.mrbyte.enums.ContentTypes;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface IMessageService {

  ContentTypes getContentTypeForOutput(Message message);

  BotApiMethod<?> respondOnMessage(Message message);
}
