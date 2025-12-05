package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.external.service.YouTubeSearchService;
import be.bencouwberghs.timeless_songs.model.SearchVideoResult;
import be.bencouwberghs.timeless_songs.service.mapper.MapSearchVideoResult;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.VideoContentDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

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

        List<SearchResult> searchResults = new ArrayList<>();
        SearchVideoResult searchVideoResult = SearchVideoResult.builder()
                .videoId("testId1")
                .build();
        List<SearchVideoResult> searchVideoResults = List.of(searchVideoResult);
        List<VideoContentDetails> contentDetails = new ArrayList<>();

        when(youTubeSearchService.searchByBandAndSong(bandName, songName))
                .thenReturn(searchResults);

        when(youTubeSearchService.findContentDetails(List.of("testId1"))).thenReturn(contentDetails);

        try (MockedStatic<MapSearchVideoResult> mockedStatic = mockStatic(MapSearchVideoResult.class)) {
            mockedStatic.when(() -> MapSearchVideoResult.map(searchResults))
                    .thenReturn(searchVideoResults);

            mockedStatic.when(() -> MapSearchVideoResult.mapDurations(searchVideoResults, contentDetails))
                    .thenAnswer(inv -> null);

            searchVideosService.searchByBandAndSong(bandName, songName);

            verify(youTubeSearchService).searchByBandAndSong(bandName, songName);
            mockedStatic.verify(() -> MapSearchVideoResult.map(searchResults));
            verify(youTubeSearchService).findContentDetails(List.of("testId1"));
            mockedStatic.verify(() -> MapSearchVideoResult.mapDurations(searchVideoResults, contentDetails));
        }
    }
}
