package be.bencouwberghs.timeless_songs.service.exception;

public class UserInputException extends RuntimeException {
    public UserInputException(String userInputException) {
        super(userInputException);
    }
}
