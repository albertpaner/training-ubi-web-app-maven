package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        try (InputStream input = new FileInputStream(new File("db.properties"))) {
            Properties prop = new Properties();
            prop.load(input);

            driver = prop.getProperty("DRIVER");
            url = prop.getProperty("URL");
            username = prop.getProperty("USERNAME");
            password = prop.getProperty("PASSWORD");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public DBConnection() {
    }

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        if ((username == null) || (password == null) || (username.trim().length() == 0)
                || (password.trim().length() == 0)) {
            return DriverManager.getConnection(url);
        } else {
            return DriverManager.getConnection(url, username, password);
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
