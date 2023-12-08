package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import model.Bean.UtenteBean;
import model.Dao.UtenteDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class EncryptJwt {

	private static String secretKey;

	public EncryptJwt() {}

	/**
 * This method is used to issue a JWT token for a specific user.
 * It first retrieves the user from the database using the provided user ID.
 * Then, it sets the issue date to the current date and the expiration date to 24 hours later.
 * Finally, it loads a secret key from a properties file to be used for token encryption.
 *
 * @param userID The ID of the user for whom the token is to be issued.
 * @return The issued JWT token as a string.
 * @throws ClassNotFoundException if the JDBC Driver is not found.
 * @throws SQLException if there is an error while retrieving the user from the database.
 * @throws IOException if there is an error reading the properties file.
 */
	public static String issueToken(int userID) throws ClassNotFoundException, SQLException {
	
		UtenteDao utenteDao = new UtenteDao();
		UtenteBean userReceiving = utenteDao.findById(userID);


		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + 24 * 60 * 60 * 1000);

		try (InputStream input = new FileInputStream(new File("key.properties"))) {
			Properties prop = new Properties();
			prop.load(input);

			secretKey = prop.getProperty("PRIVATE_KEY");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		String jwtUser = JWT.create().withIssuer("training").withClaim("email", userReceiving.getEmail())
				.withClaim("nome", userReceiving.getNome()).withClaim("cognome", userReceiving.getCognome())
				.withExpiresAt(expirationDate).sign(Algorithm.HMAC256(secretKey));

		return jwtUser;
	}
}
