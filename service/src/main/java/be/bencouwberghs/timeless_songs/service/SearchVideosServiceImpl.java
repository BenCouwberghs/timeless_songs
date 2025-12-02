package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.external.service.YouTubeSearchService;
import be.bencouwberghs.timeless_songs.model.SearchVideoResult;
import be.bencouwberghs.timeless_songs.service.mapper.MapSearchVideoResult;
import com.google.api.services.youtube.model.VideoContentDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SearchVideosServiceImpl implements SearchVideosService{
    private final YouTubeSearchService youTubeSearchService;

    public SearchVideosServiceImpl(YouTubeSearchService youTubeSearchService) {
        this.youTubeSearchService = youTubeSearchService;
    }

    public List<SearchVideoResult> searchByBandAndSong(String bandName, String songName) throws IOException {
        List<SearchVideoResult> searchVideoResults = MapSearchVideoResult.map(youTubeSearchService
                .searchByBandAndSong(bandName, songName));

        List<String> videoIds = searchVideoResults.stream()
                .map(SearchVideoResult::getVideoId)
                .toList();

        List<VideoContentDetails> contentDetails = youTubeSearchService.findContentDetails(videoIds);
        MapSearchVideoResult.mapDurations(searchVideoResults, contentDetails);
        return searchVideoResults;
    }
}
