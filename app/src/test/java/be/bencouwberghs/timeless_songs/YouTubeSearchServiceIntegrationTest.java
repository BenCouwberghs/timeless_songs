package be.bencouwberghs.timeless_songs;

import be.bencouwberghs.timeless_songs.external.service.YouTubeSearchService;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.VideoContentDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Import(TestAuditingConfig.class)
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

    @Test
    void findContentDetails() throws IOException {
        String bandName = "The Beatles";
        String songName = "Let It Be";

        List<SearchResult> results = youTubeSearchService.searchByBandAndSong(bandName, songName);
        List<String> videoIds = new ArrayList<>();

        videoIds.add(results.getFirst().getId().getVideoId());
        videoIds.add(results.get(1).getId().getVideoId());

        List<VideoContentDetails> contentDetails = youTubeSearchService.findContentDetails(videoIds);

        assertNotNull(contentDetails);
        assertFalse(contentDetails.isEmpty());
    }
}
