package exceptions;

public class LoginPasswordFailedException extends Exception{
    public LoginPasswordFailedException(String message) {
        super(message);
    }
}
