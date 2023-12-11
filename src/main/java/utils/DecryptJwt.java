package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.File;
import java.io.FileInputStream;

public class DecryptJwt {
    private static String secretKey;

    public DecryptJwt() {}

/**
 * This method is used to decode a JWT token using a secret key.
 * The secret key is loaded from a properties file.
 * The method then verifies the token's issuer and signature using the secret key.
 *
 * @param token The JWT token to be decoded.
 * @return The decoded JWT token.
 * @throws IOException if there is an error reading the properties file.
 * @throws JWTVerificationException if the token's signature is invalid or if the token's issuer is invalid.
 */
    public static DecodedJWT decodeToken(String token) {

        try (InputStream input = new FileInputStream(new File("key.properties"))) {
            Properties prop = new Properties();
            prop.load(input);

            secretKey = prop.getProperty("PRIVATE_KEY");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(secretKey))
                .withIssuer("auth0")
                .build()
                .verify(token);

        return jwt;
    }
}