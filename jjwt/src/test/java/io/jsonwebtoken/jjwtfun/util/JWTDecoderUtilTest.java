package io.jsonwebtoken.jjwtfun.util;

import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JWTDecoderUtilTest {

    private final static String SIMPLE_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkJhZWxkdW5nIFVzZXIiLCJpYXQiOjE1MTYyMzkwMjJ9";
    private final static String SIGNED_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkJhZWxkdW5nIFVzZXIiLCJpYXQiOjE1MTYyMzkwMjJ9.qH7Zj_m3kY69kxhaQXTa-ivIpytKXXjZc1ZSmapZnGE";

    @Test
    @DisplayName("Given a simple token with no signature, when decoding, then string of header and payload are returned.")
    void givenSimpleToken_whenDecoding_thenStringOfHeaderPayloadAreReturned() {
        assertThat(JWTDecoderUtil.decodeJWTToken(SIMPLE_TOKEN))
          .contains(SignatureAlgorithm.HS256.getValue());
    }

    @Test
    @DisplayName("Given a signed token, when decoding with an invalid secret, then the integrity is not validated.")
    void givenSignedToken_whenDecodingWithInvalidSecret_thenIntegrityIsNotValidated() {
        assertThatThrownBy(() -> JWTDecoderUtil.decodeJWTToken(SIGNED_TOKEN, "BAD_SECRET"))
          .hasMessage("Could not verify JWT token integrity!");
    }

    @Test
    @DisplayName("Given a signed token, when decoding with a valid secret, then the integrity is validated.")
    void givenSignedToken_whenDecodingWithValidSecret_thenIntegrityIsValidated() throws Exception {
        assertThat(JWTDecoderUtil.decodeJWTToken(SIGNED_TOKEN, "MySecretKey"))
          .contains(SignatureAlgorithm.HS256.getValue());
    }
}