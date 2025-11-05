package be.bencouwberghs.timeless_songs.external.service;

import com.google.api.services.youtube.model.SearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class YouTubeSearchServiceIntegrationTest {

    @Autowired
    private YouTubeSearchService youTubeSearchService;

    @Test
    void searchByBandAndSong() throws IOException {
        String bandName = "The Beatles";
        String songName = "Let It Be";

        List<SearchResult> results = youTubeSearchService.searchByBandAndSong(bandName, songName);

        assertNotNull(results);
        assertFalse(results.isEmpty());
    }
}
