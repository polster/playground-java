package io.dietschi.examples.sb.authtokencallout.oauth;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class TokenInterceptor implements ClientHttpRequestInterceptor {

  private final OAuthAccess access;
  private AuthToken token;

  @Override
  public ClientHttpResponse intercept(HttpRequest request,
                                      byte[] body,
                                      ClientHttpRequestExecution execution) throws IOException {

    if (token == null || token.getExpiryDate().before(new Date())) {
      token = access.doRequest();
    }

    request
        .getHeaders()
        .add(
            "Authorization",
            "Bearer " + token.getAccessToken()
        );

    return execution.execute(request, body);
  }
}
