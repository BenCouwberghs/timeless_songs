package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void handleUserInputException() {
        UserInputException userInputException = new UserInputException("No band name provided.");

        ResponseEntity<String> response = globalExceptionHandler.handleUserInputException(userInputException);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("No band name provided.");
    }

    @Test
    void handleGenericException() {
        Exception exception = new Exception("An exception has occurred.");

        ResponseEntity<String> response = globalExceptionHandler.handleGenericException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("An exception has occurred.");
    }
}
