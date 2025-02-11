package amg.mrbyte.app;

public class Constants {

  public static final String PROMPT_CONTENT_ANALYZER =
      "You are an efficient content types determiner. Now analyze the request '{content}' and decide, to which type of content this request belongs to. \nContent types: {contentTypes} \nIMPORTANT: Return only one value from the list";
  public static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
}
