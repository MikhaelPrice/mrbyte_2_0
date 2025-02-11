package amg.mrbyte.service.ai;

import amg.mrbyte.enums.ContentTypes;
import org.springframework.ai.chat.prompt.Prompt;

public interface AiService<T> {

  Prompt createContentAnalysisPrompt(T content);

  ContentTypes analiseContent(T content);
}
