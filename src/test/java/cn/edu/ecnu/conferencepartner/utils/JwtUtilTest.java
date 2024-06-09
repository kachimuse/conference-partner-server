package cn.edu.ecnu.conferencepartner.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

@RequiredArgsConstructor
public class JwtUtilTest {

    @Test
    public void createSecretKey() {
        SecretKey key = Jwts.SIG.HS256.key().build();
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString);
    }

}
