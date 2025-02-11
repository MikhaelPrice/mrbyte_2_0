package amg.mrbyte.service.telegram.impl;

import amg.mrbyte.enums.ContentTypes;
import amg.mrbyte.service.ai.impl.TextAiService;
import amg.mrbyte.service.telegram.IMessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public final class TextMessageService implements IMessageService {

  @Override
  public ContentTypes getType(Message message) {
    String inputText = message.getText();
    TextAiService aiService = new TextAiService();
    return aiService.analiseInput(inputText);
  }

  @Override
  public BotApiMethod<?> respond(Message message) {
    ContentTypes contentType = getType(message);

    if (contentType == ContentTypes.TEXT) {
      TextAiService textAiService = new TextAiService();
      String aiResponse = textAiService.generateOutput(message.getText());
      String chatId = message.getChatId().toString();
      return new SendMessage(chatId, aiResponse);
    }

    return null;
  }
}
