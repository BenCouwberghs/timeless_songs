package be.bencouwberghs.timeless_songs;

import be.bencouwberghs.timeless_songs.model.Genre;
import be.bencouwberghs.timeless_songs.model.dto.GenreDto;
import be.bencouwberghs.timeless_songs.repository.GenreRepository;
import be.bencouwberghs.timeless_songs.service.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Import(TestAuditingConfig.class)
public class GenreIntegrationTest {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreService genreService;

    @Test
    void fetchAllGenres() {
        Genre genre1 = Genre.builder()
                .description("testDescription 1")
                .build();

        Genre genre2 = Genre.builder()
                .description("testDescription 2")
                .build();

        genreRepository.save(genre1);
        genreRepository.save(genre2);

        List<GenreDto> results = genreService.fetchAllGenres();
        assertEquals(2, results.size());
    }
}
