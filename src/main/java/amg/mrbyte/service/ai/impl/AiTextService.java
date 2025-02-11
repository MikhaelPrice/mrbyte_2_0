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
public class AiTextService implements AiService<String> {

  private static final String KEY_CONTENT = "content";
  private static final String KEY_CONTENT_TYPES = "contentTypes";

  @Override
  public Prompt createContentAnalysisPrompt(String content) {
    PromptTemplate promptTemplate = new PromptTemplate(Constants.PROMPT_CONTENT_ANALYZER);
    return promptTemplate.create(
        Map.of(KEY_CONTENT, content, KEY_CONTENT_TYPES, ContentTypes.names()));
  }

  @Override
  public ContentTypes analiseContent(String content) {
    OpenAiChatModel textChatModel = createAiChatModel();
    ChatClient chatClient = ChatClient.builder(textChatModel).build();

    Prompt contentAnalysisPrompt = createContentAnalysisPrompt(content);

    return ContentTypes.valueOf(chatClient.prompt(contentAnalysisPrompt).call().content());
  }

  private OpenAiChatModel createAiChatModel() {
    OpenAiApi openAiApi = new OpenAiApi(OPENAI_API_KEY);
    var openAiChatOptions =
        OpenAiChatOptions.builder()
            .model(OpenAiApi.ChatModel.GPT_4_O_MINI)
            .temperature(0.2)
            .build();
    return new OpenAiChatModel(openAiApi, openAiChatOptions);
  }
}
