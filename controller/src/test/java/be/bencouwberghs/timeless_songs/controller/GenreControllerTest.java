package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.dto.GenreDto;
import be.bencouwberghs.timeless_songs.service.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    @Test
    void getGenre() {
        GenreDto genreDto1 = GenreDto.builder()
                .id(1L)
                .description("testDescription 1")
                .build();

        Long id = 1L;

        when(genreService.fetchGenre(id)).thenReturn(genreDto1);
        genreController.getGenre(id);

        verify(genreService).fetchGenre(id);
    }

    @Test
    void getAllGenres() {
        GenreDto genreDto2 = GenreDto.builder()
                .id(2L)
                .description("testDescription 2")
                .build();

        GenreDto genreDto3 = GenreDto.builder()
                .id(3L)
                .description("testDescription 3")
                .build();

        when(genreService.fetchAllGenres()).thenReturn(List.of(genreDto2, genreDto3));
        genreController.getAllGenres();

        verify(genreService).fetchAllGenres();
    }
}
