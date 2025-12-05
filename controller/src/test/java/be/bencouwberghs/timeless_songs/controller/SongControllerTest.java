package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import be.bencouwberghs.timeless_songs.service.SongService;
import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
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

        doNothing().when(validateEntities).validateSong(songDto);

        songController.addSong(songDto);

        verify(songService).addSong(songDto);
    }

    @Test
    void modifySong() throws UserInputException {
        SongDto songDto2 = SongDto.builder()
                .id(2L)
                .name("song 2")
                .year(1995)
                .wikiLinkPage("testLink 2")
                .build();

        Long id = 2L;

        doNothing().when(validateEntities).validateSong(songDto2);

        songController.modifySong(songDto2, id);

        verify(songService).modifySong(songDto2, id);
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

        songController.deleteSong(id);

        verify(songService).deleteSongById(id);
    }

    @Test
    void getAllSongs() {
        SongDto songDto4 = new SongDto() {{
            setName("song 4");
            setYear(1995);
            setWikiLinkPage("testLink 4");
        }};

        SongDto songDto5 = new SongDto() {{
            setName("song 5");
            setYear(1995);
            setWikiLinkPage("testLink 5");
        }};

        when(songService.fetchAllSongs()).thenReturn(List.of(songDto4, songDto5));
        var songList = songController.getAllSongs();

        assertThat(songList).isNotNull();
        assertThat(songList.size()).isEqualTo(2);
    }

    @Test
    void getSong() {
        SongDto songDto6 = new SongDto() {{
            setId(6L);
            setName("song 6");
            setYear(1995);
            setWikiLinkPage("testLink 6");
        }};

        Long id = 6L;

        when(songService.fetchSong(id)).thenReturn(songDto6);
        songController.getSong(id);

        verify(songService).fetchSong(id);
    }
}