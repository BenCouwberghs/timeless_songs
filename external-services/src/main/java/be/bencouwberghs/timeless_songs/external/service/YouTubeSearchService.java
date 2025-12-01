package be.bencouwberghs.timeless_songs.external.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class YouTubeSearchService {

    private static final String APPLICATION_NAME = "YouTubeSearchApp";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private String API_KEY;
    private YouTube youtubeService;

    public YouTubeSearchService(@Value("${youTube.api.key}") String API_KEY) throws GeneralSecurityException, IOException {
        this.API_KEY = API_KEY;
        youtubeService = new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                request -> {}
        ).setApplicationName(APPLICATION_NAME).build();
    }

    public YouTubeSearchService(String apiKey, YouTube youtubeService) {
        this.API_KEY = apiKey;
        this.youtubeService = youtubeService;
    }

    public List<SearchResult> searchByBandAndSong(String bandName, String songName) throws IOException {
        YouTube.Search.List request = youtubeService.search()
                .list("snippet")
                .setQ(bandName + " " + songName)
                .setType("video")
                .setMaxResults(20L)
                .setKey(API_KEY);

        SearchListResponse response = request.execute();
        return response.getItems();
    }

    public List<VideoContentDetails> findContentDetails(List<String> videoIds) throws IOException {
        YouTube.Videos.List videoRequest = youtubeService.videos()
                .list("contentDetails")
                .setId(String.join(",", videoIds))
                .setKey(API_KEY);

        VideoListResponse videoResponse = videoRequest.execute();
        List<Video> videoItems = videoResponse.getItems();

        List<VideoContentDetails> contentDetails = new ArrayList<>();

        videoItems.forEach(video -> contentDetails.add(video.getContentDetails()));

        return contentDetails;
    }
}
