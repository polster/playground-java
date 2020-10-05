package io.dietschi.examples.sb.authtokencallout.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
@ComponentScan(value = { "io.dietschi.examples.sb.authtokencallout.oauth" })
public class OAuthConfig {

  @Bean
  public RestTemplate restTemplate() {

    StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(
        StandardCharsets.UTF_8
    );
    stringHttpMessageConverter.setWriteAcceptCharset(false);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate
        .getMessageConverters()
        .add(
            0,
            stringHttpMessageConverter
        );

    return restTemplate;
  }

}
