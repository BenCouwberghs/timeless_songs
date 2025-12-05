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
        BandDto bandDto1 = BandDto.builder()
                .id(1L)
                .name("band 1")
                .linkWikiPage("testLink 1")
                .comments("testComments 1")
                .build();

        SongDto songDto1 = new SongDto();

        songDto1.setName("song 1");
        songDto1.setYear(1990);
        songDto1.setBandDto(bandDto1);

        songService.addSong(songDto1);

        assertNotNull(songService.findSongByName("song 1"));
    }

    @Test
    void modifySong() {
        BandDto bandDto2 = BandDto.builder()
                .id(2L)
                .name("band 2")
                .linkWikiPage("testLink 2")
                .comments("testComments 2")
                .build();

        SongDto songDto2 = new SongDto();

        songDto2.setName("song 2");
        songDto2.setYear(1990);
        songDto2.setBandDto(bandDto2);

        songService.addSong(songDto2);
        songDto2 = songService.findSongByName("song 2");

        songDto2.setName("Imagine");
        songService.modifySong(songDto2, songDto2.getId());

        assertEquals(songDto2, songService.findSongByName("Imagine"));
    }

    @Test
    void deleteSong() {
        BandDto bandDto3 = BandDto.builder()
                .id(3L)
                .name("band 3")
                .linkWikiPage("testLink 3")
                .comments("testComments 3")
                .build();

        SongDto songDto3 = new SongDto();

        songDto3.setName("song 3");
        songDto3.setYear(1990);
        songDto3.setBandDto(bandDto3);

        songService.addSong(songDto3);
        songDto3 = songService.findSongByName("song 3");
        songService.deleteSongById(songDto3.getId());

        assertEquals(0, songService.fetchAllSongs().size());
    }

    @Test
    void fetchAllSongs() {
        BandDto bandDto4 = BandDto.builder()
                .id(4L)
                .name("band 4")
                .linkWikiPage("testLink 4")
                .comments("testComments 4")
                .build();

        BandDto bandDto5 = BandDto.builder()
                .id(5L)
                .name("band 5")
                .linkWikiPage("testLink 5")
                .comments("testComments 5")
                .build();

        SongDto songDto4 = new SongDto();

        songDto4.setName("song 4");
        songDto4.setYear(1990);
        songDto4.setBandDto(bandDto4);

        SongDto songDto5 = new SongDto();

        songDto5.setName("song 5");
        songDto5.setYear(1990);
        songDto5.setBandDto(bandDto5);

        songService.addSong(songDto4);
        songService.addSong(songDto5);

        assertEquals(2, songService.fetchAllSongs().size());
    }

    @Test
    void fetchAllSongsOfBand() {
        BandDto bandDto1 = new BandDto();
        bandDto1.setName("Beatles");

        bandDto1.setId(bandService.addBand(bandDto1));

        BandDto bandDto2 = new BandDto();
        bandDto2.setName("Sabaton");

        bandDto2.setId(bandService.addBand(bandDto2));

        SongDto songDto6 = new SongDto();

        songDto6.setName("song 6");
        songDto6.setYear(1990);
        songDto6.setBandDto(bandDto1);

        SongDto songDto7 = new SongDto();

        songDto7.setName("song 7");
        songDto7.setYear(1990);
        songDto7.setBandDto(bandDto1);

        SongDto songDto8 = new SongDto();

        songDto8.setName("song 8");
        songDto8.setYear(1990);
        songDto8.setBandDto(bandDto2);

        songService.addSong(songDto6);
        songService.addSong(songDto7);
        songService.addSong(songDto8);

        assertEquals(3, songService.fetchAllSongs().size());
        assertEquals(2, songService.fetchAllSongsOfBand(bandDto1.getId()).size());
    }

    @Test
    void auditSong() {
        BandDto bandDto7 = BandDto.builder()
                .id(7L)
                .name("band 7")
                .linkWikiPage("testLink 7")
                .comments("testComments 7")
                .build();


        SongDto songDto9 = new SongDto();

        songDto9.setName("song 9");
        songDto9.setYear(1990);
        songDto9.setBandDto(bandDto7);

        songService.addSong(songDto9);
        songDto9 = songService.findSongByName("song 9");

        songDto9.setName("Imagine");
        songService.modifySong(songDto9, songDto9.getId());

        Song updatedSong = songRepository.findByName("Imagine");
        assertTrue(updatedSong.getDateLastModified().isAfter(updatedSong.getCreatedDate()));
    }

    @Test
    void getSong() {
        BandDto bandDto8 = BandDto.builder()
                .id(8L)
                .name("band 8")
                .linkWikiPage("testLink 8")
                .comments("testComments 8")
                .build();

        SongDto songDto10 = new SongDto();

        songDto10.setName("song 1");
        songDto10.setYear(1990);
        songDto10.setBandDto(bandDto8);

        songService.addSong(songDto10);
        songDto10 = songService.findSongByName("song 1");

        assertEquals(songDto10, songService.fetchSong(songDto10.getId()));
    }
}
