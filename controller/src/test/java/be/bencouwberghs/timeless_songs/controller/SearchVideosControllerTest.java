package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.service.SearchVideosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SearchVideosControllerTest {
    @Mock
    private SearchVideosService searchVideosService;

    @InjectMocks
    private SearchVideosController searchVideosController;

    @Test
    void searchByBandAndSong() throws IOException {
        String bandName = "The Beatles";
        String songName = "Let it be";

        searchVideosController.searchByBandAndSong(bandName, songName);
        verify(searchVideosService).searchByBandAndSong(bandName, songName);
    }
}
