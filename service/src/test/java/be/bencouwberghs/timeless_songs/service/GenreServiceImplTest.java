package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Genre;
import be.bencouwberghs.timeless_songs.model.dto.GenreDto;
import be.bencouwberghs.timeless_songs.repository.GenreRepository;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private MapperEntities mapperEntities;

    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    void fetchGenre() {
        Genre genre1 = Genre.builder()
                .id(1L)
                .description("testDescription 1")
                .build();

        Long id = 1L;

        when(genreRepository.getReferenceById(id)).thenReturn(genre1);
        genreService.fetchGenre(id);
        verify(genreRepository).getReferenceById(id);
    }

    @Test
    void fetchAllGenres() {
        GenreDto genreDto2 = GenreDto.builder()
                .id(2L)
                .description("testDescription 2")
                .build();

        GenreDto genreDto3 = GenreDto.builder()
                .id(3L)
                .description("testDescription 3")
                .build();

        when(mapperEntities.mapGenreEntitiesToDtos(genreRepository.findAll())).thenReturn(List.of(genreDto2, genreDto3));
        var genreList = genreService.fetchAllGenres();

        assertThat(genreList).isNotNull();
        assertThat(genreList).size().isEqualTo(2);
    }
}
