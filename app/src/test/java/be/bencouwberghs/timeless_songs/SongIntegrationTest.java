package be.bencouwberghs.timeless_songs;

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

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Import(TestAuditingConfig.class)
public class SongIntegrationTest {
    @Autowired
    private SongRepository bandRepository;

    @Autowired
    private SongService bandService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void addSong() {

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
