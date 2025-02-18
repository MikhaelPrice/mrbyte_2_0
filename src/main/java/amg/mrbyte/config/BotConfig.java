package amg.mrbyte.config;

import amg.mrbyte.app.AppBotRunner;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("bot")
@Data
public class BotConfig {

  private String name;
  private String token;

  @Bean
  public AppBotRunner appBotRunner(BotConfig botConfig) {
    return new AppBotRunner(token, botConfig);
  }
}
