package amg.mrbyte.service.ai.impl;

import amg.mrbyte.app.Constants;
import amg.mrbyte.enums.ContentTypes;
import amg.mrbyte.service.ai.AiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;

import java.util.Map;

import static amg.mrbyte.app.Constants.OPENAI_API_KEY;

@Service
public class TextAiService implements AiService<OpenAiChatModel, Prompt, String> {

  private static final String KEY_INPUT_CONTENT = "inputContent";
  private static final String KEY_CONTENT_TYPES = "contentTypes";

  private final ChatClient chatClient = ChatClient.builder(createAiModel()).build();

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
  public Prompt createAnalysisPrompt(String inputContent) {
    PromptTemplate promptTemplate = new PromptTemplate(Constants.PROMPT_INPUT_CONTENT_ANALYZER);
    return promptTemplate.create(
        Map.of(KEY_INPUT_CONTENT, inputContent, KEY_CONTENT_TYPES, ContentTypes.names()));
  }

  @Override
  public ContentTypes analiseInput(String inputContent) {
    return ContentTypes.valueOf(
        chatClient.prompt(createAnalysisPrompt(inputContent)).call().content());
  }

  @Override
  public String generateOutput(String inputContent) {
    return chatClient.prompt().user(inputContent).call().content();
  }
}
