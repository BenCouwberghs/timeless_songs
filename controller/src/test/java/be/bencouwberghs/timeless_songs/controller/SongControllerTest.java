package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import be.bencouwberghs.timeless_songs.service.SongService;
import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import be.bencouwberghs.timeless_songs.service.validator.ValidateEntities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongControllerTest {

    @Mock
    private SongService songService;

    @Mock
    private MapperEntities mapperEntities;

    @Mock
    private ValidateEntities validateEntities;

    @InjectMocks
    private SongController songController;

    @Test
    void addSong() throws UserInputException {
        SongDto songDto = SongDto.builder()
                .name("song 1")
                .year(1995)
                .wikiLinkPage("testLink 1")
                .build();

        Song song = new Song() {{
            setName("song 1");
            setYear(1995);
            setLinkWikiPage("testLink 1");
        }};

        doNothing().when(validateEntities).validateSong(songDto);
        when(mapperEntities.mapSongDtoToSongEntity(songDto)).thenReturn(song);

        songController.addSong(songDto);

        verify(songService).addSong(song);
    }

    @Test
    void modifySong() throws UserInputException {
        SongDto songDto2 = SongDto.builder()
                .name("song 2")
                .year(1995)
                .wikiLinkPage("testLink 2")
                .build();

        Song song2 = new Song() {{
            setName("song 2");
            setYear(1995);
            setLinkWikiPage("testLink 2");
        }};

        doNothing().when(validateEntities).validateSong(songDto2);
        when(mapperEntities.mapSongDtoToSongEntity(songDto2)).thenReturn(song2);

        songController.modifySong(songDto2);

        verify(songService).modifySong(song2);
    }

    @Test
    void deleteSong() {
        Song song3 = new Song() {{
            setId(3L);
            setName("song 3");
            setYear(1995);
            setLinkWikiPage("testLink 3");
        }};

        Long id = 3L;

        when(songService.fetchSong(id)).thenReturn(song3);
        songController.deleteSong(id);

        verify(songService).deleteSong(song3);
    }

    @Test
    void getAllSongs() {
        Song song4 = new Song() {{
            setName("song 4");
            setYear(1995);
            setLinkWikiPage("testLink 4");
        }};

        Song song5 = new Song() {{
            setName("song 5");
            setYear(1995);
            setLinkWikiPage("testLink 5");
        }};

        when(songService.fetchAllSongs()).thenReturn(List.of(song4, song5));
        var songList = songController.getAllSongs();

        assertThat(songList).isNotNull();
        assertThat(songList.size()).isEqualTo(2);
    }

    @Test
    void getSong() {
        Song song6 = new Song() {{
            setId(6L);
            setName("song 6");
            setYear(1995);
            setLinkWikiPage("testLink 6");
        }};

        Long id = 6L;

        when(songService.fetchSong(id)).thenReturn(song6);
        songController.getSong(id);

        verify(songService).fetchSong(id);
    }
}