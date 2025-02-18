package amg.mrbyte.service.ai.chat;

import amg.mrbyte.enums.ContentTypes;

public interface IChatAiService<M, T> {

  M createAiModel();

  ContentTypes analyseInputContentType(T inputContent);

  T generateResponseOnInput(T inputContent);
}
