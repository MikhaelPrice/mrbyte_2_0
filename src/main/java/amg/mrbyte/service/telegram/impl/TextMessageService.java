package amg.mrbyte.service.telegram.impl;

import amg.mrbyte.enums.ContentTypes;
import amg.mrbyte.service.ai.AiService;
import amg.mrbyte.service.ai.impl.AiTextService;
import amg.mrbyte.service.telegram.IMessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public final class TextMessageService implements IMessageService {

  @Override
  public ContentTypes getType(Message message) {
    AiService<String> aiService = new AiTextService();
    String content = message.getText();
    return aiService.analiseContent(content);
  }

  @Override
  public BotApiMethod<?> respond(Message message) {
    ContentTypes contentType = getType(message);
    return new SendMessage(message.getChatId().toString(), contentType.name());
  }
}
