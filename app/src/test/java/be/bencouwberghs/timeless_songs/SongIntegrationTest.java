package be.bencouwberghs.timeless_songs;

import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.repository.SongRepository;
import be.bencouwberghs.timeless_songs.service.SongService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Import(TestAuditingConfig.class)
public class SongIntegrationTest {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void addSong() {
        Song song1 = new Song();

        song1.setName("song 1");
        song1.setYear(1990);

        songService.addSong(song1);

        assertNotNull(songService.fetchAllSongs());
    }

    @Test
    void modifySong() {

    }

    @Test
    void deleteSong() {

    }

    @Test
    void fetchAllSongs() {

    }

    @Test
    void fetchAllSongsOfBand() {

    }
}
