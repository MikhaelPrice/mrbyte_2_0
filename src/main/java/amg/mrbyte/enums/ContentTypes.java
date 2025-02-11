package amg.mrbyte.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum ContentTypes {
  TEXT("TEXT"),
  IMAGE("IMAGE"),
  DOCUMENT("DOCUMENT"),
  AUDIO("AUDIO"),
  VIDEO("VIDEO");

  private final String type;

  public static String names() {
    return Arrays.stream(values()).map(Enum::name).collect(Collectors.joining(", "));
  }
}
