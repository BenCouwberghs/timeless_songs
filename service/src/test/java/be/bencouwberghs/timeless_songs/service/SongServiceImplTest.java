package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.repository.SongRepository;
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
public class SongServiceImplTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongServiceImpl songService;

    @Test
    void addSong() {
        Song song1 = new Song() {{
            setId(1L);
            setName("song1");
            setYear(1990);
        }};

        songService.addSong(song1);
        verify(songRepository).save(song1);
    }

    @Test
    void modifySong() {
        Song song2 = new Song() {{
            setId(2L);
            setName("song2");
            setYear(1990);
        }};

        when(songRepository.findByName(song2.getName())).thenReturn(song2);

        songService.modifySong(song2);
        verify(songRepository).save(song2);
    }

    @Test
    void deleteSong() {
        Song song3 = new Song() {{
            setId(3L);
            setName("song3");
            setYear(1990);
        }};

        when(songRepository.getReferenceById(song3.getId())).thenReturn(song3);

        songService.deleteSongById(song3.getId());
        verify(songRepository).delete(song3);
    }

    @Test
    void fetchAllSongs() {
        Song song4 = new Song() {{
            setId(4L);
            setName("song4");
            setYear(1990);
        }};

        Song song5 = new Song() {{
            setId(5L);
            setName("song5");
            setYear(1990);
        }};

        when(songRepository.findAll()).thenReturn(List.of(song4, song5));
        var songList = songService.fetchAllSongs();

        assertThat(songList).isNotNull();
        assertThat(songList.size()).isEqualTo(2);
    }

    @Test
    void fetchAllSongsOfBand() {
        Band band = new Band() {{
            setId(1L);
            setName("band");
        }};

        Song song4 = new Song() {{
            setId(4L);
            setName("song4");
            setYear(1990);
            setBand(band);
        }};

        Song song5 = new Song() {{
            setId(5L);
            setName("song5");
            setYear(1990);
            setBand(band);
        }};

        when(songRepository.findAllByBand(band)).thenReturn(List.of(song4, song5));
        var songList = songService.fetchAllSongsOfBand(band);

        assertThat(songList).isNotNull();
        assertThat(songList.size()).isEqualTo(2);
    }

    @Test
    void getSong() {
        Song song6 = new Song() {{
            setId(6L);
            setName("song6");
            setYear(1990);
        }};

        Long id = song6.getId();

        when(songRepository.getReferenceById(id)).thenReturn(song6);
        songService.fetchSong(id);

        verify(songRepository).getReferenceById(id);
    }
}
