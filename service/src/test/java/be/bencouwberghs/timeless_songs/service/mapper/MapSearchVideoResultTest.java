package be.bencouwberghs.timeless_songs.service.mapper;

import be.bencouwberghs.timeless_songs.model.SearchVideoResult;
import com.google.api.services.youtube.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MapSearchVideoResultTest {

    public static final String MY_TITLE = "my title";
    public static final String MY_VIDEO_ID = "my video id";

    @Test
    void testTitleAndVideoId() {
        List<SearchResult> searchResults = new ArrayList<>();
        SearchResult searchResult = getSearchResult();
        searchResults.add(searchResult);
        List<SearchVideoResult> mappedResult = MapSearchVideoResult.map(searchResults);

        assert (mappedResult.getFirst().getTitle().equals(MY_TITLE));
        assert (mappedResult.getFirst().getVideoId().equals(MY_VIDEO_ID));
    }

    @Test
    void testThumbnails() {
        List<SearchResult> searchResults = new ArrayList<>();
        SearchResult searchResult = getSearchResult();

        searchResult.getSnippet().getThumbnails().setDefault(new Thumbnail());
        searchResult.getSnippet().getThumbnails().getDefault().setUrl("a");

        searchResult.getSnippet().getThumbnails().setMedium(new Thumbnail());
        searchResult.getSnippet().getThumbnails().getMedium().setUrl("b");

        searchResult.getSnippet().getThumbnails().setHigh(new Thumbnail());
        searchResult.getSnippet().getThumbnails().getHigh().setUrl("c");

        searchResult.getSnippet().getThumbnails().setStandard(new Thumbnail());
        searchResult.getSnippet().getThumbnails().getStandard().setUrl("d");

        searchResult.getSnippet().getThumbnails().setMaxres(new Thumbnail());
        searchResult.getSnippet().getThumbnails().getMaxres().setUrl("e");


        searchResults.add(searchResult);
        List<SearchVideoResult> mappedResult = MapSearchVideoResult.map(searchResults);

        assert (mappedResult.getFirst().getThumbnails().getUrlDefault().equals("a"));
        assert (mappedResult.getFirst().getThumbnails().getUrlMedium().equals("b"));
        assert (mappedResult.getFirst().getThumbnails().getUrlHigh().equals("c"));
        assert (mappedResult.getFirst().getThumbnails().getUrlStandard().equals("d"));
        assert (mappedResult.getFirst().getThumbnails().getUrlMaxRes().equals("e"));
    }

    private static SearchResult getSearchResult() {
        SearchResult searchResult = new SearchResult();
        SearchResultSnippet searchResultSnippet = new SearchResultSnippet();
        searchResultSnippet.setTitle(MY_TITLE);
        ResourceId searchResultId = new ResourceId();
        ThumbnailDetails thumbnailDetails = new ThumbnailDetails();
        searchResultId.setVideoId(MY_VIDEO_ID);
        searchResultSnippet.setThumbnails(thumbnailDetails);
        searchResult.setSnippet(searchResultSnippet);
        searchResult.setId(searchResultId);
        return searchResult;
    }
}
