package be.bencouwberghs.timeless_songs;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.repository.SongRepository;
import be.bencouwberghs.timeless_songs.service.BandService;
import be.bencouwberghs.timeless_songs.service.SongService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Import(TestAuditingConfig.class)
public class SongIntegrationTest {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;

    @Autowired
    private BandService bandService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void addSong() {
        Song song1 = new Song();

        song1.setName("song 1");
        song1.setYear(1990);

        songService.addSong(song1);

        assertTrue(songService.fetchAllSongs().contains(song1));
    }

    @Test
    void modifySong() {
        Song song2 = new Song();

        song2.setName("song 2");
        song2.setYear(1990);

        songService.addSong(song2);

        song2.setName("Imagine");
        songService.modifySong(song2);

        assertEquals(song2, songRepository.findByName("Imagine"));
    }

    @Test
    void deleteSong() {
        Song song3 = new Song();

        song3.setName("song 3");
        song3.setYear(1990);

        songService.addSong(song3);
        songService.deleteSong(song3);

        assertEquals(0, songService.fetchAllSongs().size());
    }

    @Test
    void fetchAllSongs() {
        Song song4 = new Song();

        song4.setName("song 4");
        song4.setYear(1990);

        Song song5 = new Song();

        song5.setName("song 5");
        song5.setYear(1990);

        songService.addSong(song4);
        songService.addSong(song5);

        assertEquals(2, songService.fetchAllSongs().size());
    }

    @Test
    void fetchAllSongsOfBand() {
        Band band = new Band();
        band.setName("Beatles");

        Song song6 = new Song();

        song6.setName("song 6");
        song6.setYear(1990);
        song6.setBand(band);

        Song song7 = new Song();

        song7.setName("song 7");
        song7.setYear(1990);
        song7.setBand(band);

        Song song8 = new Song();

        song8.setName("song 8");
        song8.setYear(1990);

        bandService.addBand(band);

        songService.addSong(song6);
        songService.addSong(song7);
        songService.addSong(song8);

        assertEquals(3, songService.fetchAllSongs().size());
        assertEquals(2, songService.fetchAllSongsOfBand(band).size());
    }

    @Test
    void auditSong() {
        Song song9 = new Song();

        song9.setName("song 9");
        song9.setYear(1990);

        songService.addSong(song9);
        song9.setName("Imagine");
        songService.modifySong(song9);

        Song updatedSong = songRepository.findByName("Imagine");
        assertTrue(updatedSong.getDateLastModified().isAfter(updatedSong.getCreatedDate()));
    }
}
