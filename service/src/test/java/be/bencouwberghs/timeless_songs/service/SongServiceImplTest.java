package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import be.bencouwberghs.timeless_songs.repository.SongRepository;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SongServiceImplTest {

    @Mock
    private SongRepository songRepository;

    @Mock
    private MapperEntities mapperEntities;

    @InjectMocks
    private SongServiceImpl songService;

    @Test
    void addSong() {
        SongDto songDto1 = new SongDto() {{
            setId(1L);
            setName("song1");
            setYear(1990);
        }};

        Song song1 = new Song() {{
            setId(1L);
            setName("song1");
            setYear(1990);
        }};

        when(mapperEntities.mapSongDtoToSongEntity(songDto1)).thenReturn(song1);
        songService.addSong(songDto1);
        verify(songRepository).save(song1);
    }

    @Test
    void modifySong() {
        SongDto songDto2 = new SongDto() {{
            setId(2L);
            setName("song2");
            setYear(1990);
        }};

        Song song2 = new Song() {{
            setId(2L);
            setName("song2");
            setYear(1990);
        }};

        when(songRepository.findById(songDto2.getId())).thenReturn(Optional.of(song2));
        when(songRepository.existsByNameAndIdNot(song2.getName(), song2.getId())).thenReturn(false);
        when(mapperEntities.updateSongEntityFromDto(song2, songDto2)).thenReturn(song2);

        songService.modifySong(songDto2, song2.getId());

        verify(mapperEntities).updateSongEntityFromDto(song2, songDto2);
        verify(songRepository).save(song2);
    }

    @Test
    void deleteSong() {
        Song song3 = new Song() {{
            setId(3L);
            setName("song3");
            setYear(1990);
        }};

        when(songRepository.findById(song3.getId())).thenReturn(Optional.of(song3));

        songService.deleteSongById(song3.getId());
        verify(songRepository).delete(song3);
    }

    @Test
    void fetchAllSongs() {
        SongDto songDto4 = new SongDto() {{
            setId(4L);
            setName("song4");
            setYear(1990);
        }};

        SongDto songDto5 = new SongDto() {{
            setId(5L);
            setName("song5");
            setYear(1990);
        }};

        when(mapperEntities.mapSongEntitiesToDtos(songRepository.findAll())).thenReturn(List.of(songDto4, songDto5));
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

        SongDto songDto4 = new SongDto() {{
            setId(4L);
            setName("song4");
            setYear(1990);
            setBand(band);
        }};

        SongDto songDto5 = new SongDto() {{
            setId(5L);
            setName("song5");
            setYear(1990);
            setBand(band);
        }};

        when(mapperEntities.mapSongEntitiesToDtos(songRepository.findAllByBand(band))).thenReturn(List.of(songDto4, songDto5));
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
