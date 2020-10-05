package io.dietschi.examples.sb.authtokencallout.oauth;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Component
@AllArgsConstructor
public class OAuthAccess {

  private final OAuthSettings settings;
  private final RestTemplate restTemplate;

  public AuthToken doRequest() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));

    String formData = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s",
        settings.getUserName(),
        settings.getPassword());
    HttpEntity<String> request = new HttpEntity<>(formData, headers);
    ResponseEntity<AuthToken> response = restTemplate.exchange(
        settings.getBaseUrl(),
        HttpMethod.POST,
        request,
        AuthToken.class);

    return response.getBody();
  }
}
