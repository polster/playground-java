package io.dietschi.examples.sb.authtokencallout.oauth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

import static java.util.Objects.requireNonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class AuthToken {

    private String accessToken;
    private Date expiryDate;

    @JsonCreator
    public AuthToken(@JsonProperty("access_token") String accessToken,
                     @JsonProperty("expiry_date") Date  expiryDate) {

        this.accessToken = requireNonNull(accessToken);
        this.expiryDate = requireNonNull(expiryDate);
    }
}
