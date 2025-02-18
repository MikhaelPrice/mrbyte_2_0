package amg.mrbyte.service.telegram.impl;

import amg.mrbyte.enums.ContentTypes;
import amg.mrbyte.service.ai.chat.impl.TextChatAiService;
import amg.mrbyte.service.telegram.IMessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public final class TextMessageService implements IMessageService {

  private final TextChatAiService aiTextChatService = new TextChatAiService();

  @Override
  public ContentTypes getContentTypeForOutput(Message message) {
    String inputText = message.getText();
    return aiTextChatService.analyseInputContentType(inputText);
  }

  @Override
  public BotApiMethod<?> respondOnMessage(Message message) {
    ContentTypes contentType = getContentTypeForOutput(message);
    String chatId = message.getChatId().toString();
    String textMessage = message.getText();
    if (contentType == ContentTypes.TEXT) {
      String aiResponse = aiTextChatService.generateResponseOnInput(textMessage);
      return new SendMessage(chatId, aiResponse);
    }

    return null;
  }
}
