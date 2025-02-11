package amg.mrbyte.service.ai;

import amg.mrbyte.enums.ContentTypes;

public interface AiService<C, P, T> {

  C createAiModel();

  P createAnalysisPrompt(T inputContent);

  ContentTypes analiseInput(T inputContent);

  T generateOutput(T inputContent);
}
