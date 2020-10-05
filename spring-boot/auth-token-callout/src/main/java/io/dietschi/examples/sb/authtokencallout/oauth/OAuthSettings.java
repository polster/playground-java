package io.dietschi.examples.sb.authtokencallout.oauth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "io.dietschi.examples.sb.authtokencallout.auth")
@Data
public class OAuthSettings {

  private String baseUrl;
  private String userName;
  private String password;
}
