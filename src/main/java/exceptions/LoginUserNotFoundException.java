package exceptions;

public class LoginUserNotFoundException extends Exception {
    public LoginUserNotFoundException(String message) {
        super(message);
    }
}
