package be.bencouwberghs.timeless_songs.external.service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoContentDetails;
import com.google.api.services.youtube.model.VideoListResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class YouTubeSearchServiceTest {

    @Mock
    private YouTube youTubeService;

    @Mock
    private YouTube.Videos youTubeVideos;

    @Mock
    private YouTube.Videos.List videosList;

    @Mock
    private VideoListResponse videoListResponse;

    @InjectMocks
    private YouTubeSearchService youTubeSearchService = new YouTubeSearchService("testKey", youTubeService);

    @Test
    void findContentDetails() throws IOException {
        List<String> videoIds = List.of("testId1", "testId2");

        when(youTubeService.videos()).thenReturn(youTubeVideos);
        when(youTubeVideos.list("contentDetails")).thenReturn(videosList);
        when(videosList.setId(anyString())).thenReturn(videosList);
        when(videosList.setKey(anyString())).thenReturn(videosList);

        VideoContentDetails testDetails1 = new VideoContentDetails().setDuration("PT3M10S");
        VideoContentDetails testDetails2 = new VideoContentDetails().setDuration("PT5M20S");

        Video testVideo1 = new Video().setContentDetails(testDetails1);
        Video testVideo2 = new Video().setContentDetails(testDetails2);

        when(videosList.execute()).thenReturn(videoListResponse);
        when(videoListResponse.getItems()).thenReturn(List.of(testVideo1, testVideo2));

        List<VideoContentDetails> result = youTubeSearchService.findContentDetails(videoIds);

        assertEquals(2, result.size());
        assertEquals("PT3M10S", result.get(0).getDuration());
        assertEquals("PT5M20S", result.get(1).getDuration());

        verify(youTubeService).videos();
        verify(youTubeVideos).list("contentDetails");
        verify(videosList).setId("testId1,testId2");
        verify(videosList).setKey(anyString());
        verify(videosList).execute();
    }
}
