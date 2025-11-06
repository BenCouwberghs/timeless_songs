package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.external.service.YouTubeSearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SearchVideoServiceImplTest {
    @Mock
    private YouTubeSearchService youTubeSearchService;

    @InjectMocks
    private SearchVideosServiceImpl searchVideosService;

    @Test
    void searchByBandAndSong() throws IOException {
        String bandName = "The Beatles";
        String songName = "Let it be";

        searchVideosService.searchByBandAndSong(bandName, songName);
        verify(youTubeSearchService).searchByBandAndSong(bandName, songName);
    }
}
