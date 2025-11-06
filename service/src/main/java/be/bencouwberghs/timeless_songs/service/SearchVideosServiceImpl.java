package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.external.service.YouTubeSearchService;
import be.bencouwberghs.timeless_songs.model.SearchVideoResult;
import be.bencouwberghs.timeless_songs.service.mapper.MapSearchVideoResult;
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
        return MapSearchVideoResult.map(youTubeSearchService
                .searchByBandAndSong(bandName, songName));
    }
}
