package be.bencouwberghs.timeless_songs;

import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
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
        SongDto songDto1 = new SongDto();

        songDto1.setName("song 1");
        songDto1.setYear(1990);

        songService.addSong(songDto1);

        assertNotNull(songService.findSongByName("song 1"));
    }

    @Test
    void modifySong() {
        SongDto songDto2 = new SongDto();

        songDto2.setName("song 2");
        songDto2.setYear(1990);

        songService.addSong(songDto2);
        songDto2 = songService.findSongByName("song 2");

        songDto2.setName("Imagine");
        songService.modifySong(songDto2, songDto2.getId());

        assertEquals(songDto2, songService.findSongByName("Imagine"));
    }

    @Test
    void deleteSong() {
        SongDto songDto3 = new SongDto();

        songDto3.setName("song 3");
        songDto3.setYear(1990);

        songService.addSong(songDto3);
        songDto3 = songService.findSongByName("song 3");
        songService.deleteSongById(songDto3.getId());

        assertEquals(0, songService.fetchAllSongs().size());
    }

    @Test
    void fetchAllSongs() {
        SongDto songDto4 = new SongDto();

        songDto4.setName("song 4");
        songDto4.setYear(1990);

        SongDto songDto5 = new SongDto();

        songDto5.setName("song 5");
        songDto5.setYear(1990);

        songService.addSong(songDto4);
        songService.addSong(songDto5);

        assertEquals(2, songService.fetchAllSongs().size());
    }

    @Test
    void fetchAllSongsOfBand() {
        BandDto bandDto = new BandDto();
        bandDto.setName("Beatles");

        bandService.addBand(bandDto);
        bandDto = bandService.findBandByName("Beatles");

        SongDto songDto6 = new SongDto();

        songDto6.setName("song 6");
        songDto6.setYear(1990);
        songDto6.setBandDto(bandDto);

        SongDto songDto7 = new SongDto();

        songDto7.setName("song 7");
        songDto7.setYear(1990);
        songDto7.setBandDto(bandDto);

        SongDto song8 = new SongDto();

        song8.setName("song 8");
        song8.setYear(1990);

        songService.addSong(songDto6);
        songService.addSong(songDto7);
        songService.addSong(song8);

        assertEquals(3, songService.fetchAllSongs().size());
        assertEquals(2, songService.fetchAllSongsOfBand(bandDto.getId()).size());
    }

    @Test
    void auditSong() {
        SongDto songDto9 = new SongDto();

        songDto9.setName("song 9");
        songDto9.setYear(1990);

        songService.addSong(songDto9);
        songDto9 = songService.findSongByName("song 9");

        songDto9.setName("Imagine");
        songService.modifySong(songDto9, songDto9.getId());

        Song updatedSong = songRepository.findByName("Imagine");
        assertTrue(updatedSong.getDateLastModified().isAfter(updatedSong.getCreatedDate()));
    }

    @Test
    void getSong() {
        SongDto songDto10 = new SongDto();

        songDto10.setName("song 1");
        songDto10.setYear(1990);

        songService.addSong(songDto10);
        songDto10 = songService.findSongByName("song 1");

        assertEquals(songDto10, songService.fetchSong(songDto10.getId()));
    }
}
