package amg.mrbyte.service.ai.chat.impl;

import static amg.mrbyte.app.Constants.OPENAI_API_KEY;

import amg.mrbyte.enums.ContentTypes;
import amg.mrbyte.service.ai.IPromptService;
import amg.mrbyte.service.ai.chat.AiChatService;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class AiTextChatChatService
    implements AiChatService<OpenAiChatModel, String>, IPromptService<String> {

  private final ChatClient textChatClient = ChatClient.builder(createAiModel()).build();

  @Override
  public OpenAiChatModel createAiModel() {
    OpenAiApi openAiApi = new OpenAiApi(OPENAI_API_KEY);
    var openAiChatOptions =
        OpenAiChatOptions.builder()
            .model(OpenAiApi.ChatModel.GPT_4_O_MINI)
            .temperature(0.2)
            .build();
    return new OpenAiChatModel(openAiApi, openAiChatOptions);
  }

  @Override
  public ContentTypes analyseInputContentType(String inputContent) {
    return ContentTypes.valueOf(
        textChatClient.prompt(createPromptForInputAnalysis(inputContent)).call().content());
  }

  @Override
  public String generateResponseOnInput(String inputContent) {
    return textChatClient.prompt().user(inputContent).call().content();
  }
}
