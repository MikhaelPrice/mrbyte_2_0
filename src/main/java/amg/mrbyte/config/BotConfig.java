package amg.mrbyte.config;

import amg.mrbyte.app.AppBot;
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
  public AppBot appBot(BotConfig botConfig) {
    return new AppBot(token, botConfig);
  }
}
