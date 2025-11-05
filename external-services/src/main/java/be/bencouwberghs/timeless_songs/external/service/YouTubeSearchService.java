package be.bencouwberghs.timeless_songs.external.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class YouTubeSearchService {

    private static final String APPLICATION_NAME = "YouTubeSearchApp";
    private static final String API_KEY = System.getenv("API_KEY");
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private YouTube youtubeService;

    public YouTubeSearchService() throws GeneralSecurityException, IOException {
        youtubeService = new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                request -> {}
        ).setApplicationName(APPLICATION_NAME).build();
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
}
