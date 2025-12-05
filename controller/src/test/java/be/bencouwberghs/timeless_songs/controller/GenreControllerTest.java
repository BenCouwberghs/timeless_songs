package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.service.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    @Test
    void getAllGenres() {
        genreController.getAllGenres();
        verify(genreService).fetchAllGenres();
    }
}
