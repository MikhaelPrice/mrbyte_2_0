package amg.mrbyte.service.ai;

import amg.mrbyte.app.Constants;
import amg.mrbyte.enums.ContentTypes;
import java.util.Map;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

public interface IPromptService<T> {

  String KEY_INPUT_CONTENT = "inputContent";
  String KEY_CONTENT_TYPES = "contentTypes";

  default Prompt createPromptForInputAnalysis(T input) {
    PromptTemplate promptTemplate = new PromptTemplate(Constants.PROMPT_INPUT_CONTENT_ANALYZER);
    return promptTemplate.create(
        Map.of(KEY_INPUT_CONTENT, input, KEY_CONTENT_TYPES, ContentTypes.names()));
  }
}
