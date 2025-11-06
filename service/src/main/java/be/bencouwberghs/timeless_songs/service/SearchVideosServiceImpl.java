package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.external.service.YouTubeSearchService;
import be.bencouwberghs.timeless_songs.model.SearchVideoClipThumbnails;
import be.bencouwberghs.timeless_songs.model.SearchVideoResult;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.ThumbnailDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchVideosServiceImpl implements SearchVideosService{
    private final YouTubeSearchService youTubeSearchService;

    public SearchVideosServiceImpl(YouTubeSearchService youTubeSearchService) {
        this.youTubeSearchService = youTubeSearchService;
    }

    public List<SearchVideoResult> searchByBandAndSong(String bandName, String songName) throws IOException {
        List<SearchResult> searchResults = youTubeSearchService.searchByBandAndSong(bandName, songName);

        List<SearchVideoResult> searchVideoResults = new ArrayList<>();
        for (SearchResult searchResult : searchResults) {
            SearchVideoResult searchVideoResult = SearchVideoResult.builder()
                    .source("youtube")
                    .title(searchResult.getSnippet().getTitle())
                    .videoId(searchResult.getId().getVideoId())
                    .build();

            ThumbnailDetails thumbnailDetails = searchResult.getSnippet().getThumbnails();
            SearchVideoClipThumbnails searchVideoClipThumbnails = SearchVideoClipThumbnails.builder()
                    .urlDefault(thumbnailDetails.getDefault().getUrl())
                    .urlMedium(thumbnailDetails.getMedium().getUrl())
                    .urlHigh(thumbnailDetails.getHigh().getUrl())
                    .urlStandard(thumbnailDetails.getStandard().getUrl())
                    .urlMaxRes(thumbnailDetails.getMaxres().getUrl())
                    .build();

            searchVideoResult.setThumbnails(searchVideoClipThumbnails);
            searchVideoResults.add(searchVideoResult);
        }
        return searchVideoResults;
    }
}
