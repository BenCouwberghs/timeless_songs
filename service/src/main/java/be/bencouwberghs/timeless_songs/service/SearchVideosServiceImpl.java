package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.external.service.YouTubeSearchService;
import be.bencouwberghs.timeless_songs.model.SearchVideoResult;
import be.bencouwberghs.timeless_songs.service.mapper.MapSearchVideoResult;
import com.google.api.services.youtube.model.SearchResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SearchVideosServiceImpl implements SearchVideosService{
    private final YouTubeSearchService youTubeSearchService;
    private final MapSearchVideoResult mapSearchVideoResult;

    public SearchVideosServiceImpl(YouTubeSearchService youTubeSearchService, MapSearchVideoResult mapSearchVideoResult) {
        this.youTubeSearchService = youTubeSearchService;
        this.mapSearchVideoResult = mapSearchVideoResult;
    }

    public List<SearchVideoResult> searchByBandAndSong(String bandName, String songName) throws IOException {
        List<SearchResult> searchResults = youTubeSearchService.searchByBandAndSong(bandName, songName);

        return searchResults.stream()
                .map(mapSearchVideoResult::map)
                .toList();
    }
}
