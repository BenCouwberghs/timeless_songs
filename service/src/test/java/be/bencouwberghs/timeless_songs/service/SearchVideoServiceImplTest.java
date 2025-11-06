package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.external.service.YouTubeSearchService;
import be.bencouwberghs.timeless_songs.model.SearchVideoResult;
import be.bencouwberghs.timeless_songs.service.mapper.MapSearchVideoResult;
import com.google.api.services.youtube.model.SearchResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchVideoServiceImplTest {
    @Mock
    private YouTubeSearchService youTubeSearchService;

    @Mock
    private MapSearchVideoResult mapSearchVideoResult;

    @InjectMocks
    private SearchVideosServiceImpl searchVideosService;

    @Test
    void searchByBandAndSong() throws IOException {
        String bandName = "The Beatles";
        String songName = "Let it be";

        SearchResult searchResult = new SearchResult();

        when(youTubeSearchService.searchByBandAndSong(bandName, songName))
                .thenReturn(List.of(searchResult));

        when(mapSearchVideoResult.map(searchResult)).thenReturn(SearchVideoResult.builder().build());

        searchVideosService.searchByBandAndSong(bandName, songName);

        verify(youTubeSearchService).searchByBandAndSong(bandName, songName);
        verify(mapSearchVideoResult).map(searchResult);
    }
}
