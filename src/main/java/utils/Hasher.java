package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class Hasher {

    /**
     * Hashes a password using the SHA-256 algorithm.
     *
     * @param password the password to be hashed
     * @return the hashed password
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    public static String hashPassword(String password) {
	String hashedPassword;
	try {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
	    BigInteger number = new BigInteger(1, hash);
	    hashedPassword = number.toString(16);
	} catch (NoSuchAlgorithmException e) {
	    throw new RuntimeException(e);
	}
    
	return hashedPassword;
    }

/**
 * This method is used to hash a password with a salt. The salt is retrieved from a properties file.
 * The salt and password are concatenated and then hashed using SHA-256 algorithm.
 * The hashed value is then converted to a hexadecimal string.
 *
 * @param password The password to be hashed.
 * @return The hashed and salted password as a hexadecimal string.
 * @throws RuntimeException if the SHA-256 algorithm is not found or if there is an error reading the properties file.
 */
    public static String hashSaltPassword(String password) {
        String hashedSaltedPassword;
        try {
            Properties prop = new Properties();
            FileInputStream input = new FileInputStream(new File("key.properties"));
            prop.load(input);
            String privateKey = prop.getProperty("PRIVATE_KEY");

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String saltedPassword = privateKey + password;
            byte[] hash = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            hashedSaltedPassword = number.toString(16);
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
        return hashedSaltedPassword;
    }
}