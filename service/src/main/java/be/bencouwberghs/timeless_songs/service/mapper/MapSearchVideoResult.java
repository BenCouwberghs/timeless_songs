package be.bencouwberghs.timeless_songs.service.mapper;

import be.bencouwberghs.timeless_songs.model.SearchVideoClipThumbnails;
import be.bencouwberghs.timeless_songs.model.SearchVideoResult;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.ThumbnailDetails;
import org.springframework.stereotype.Component;

@Component
public class MapSearchVideoResult {
    public SearchVideoResult map(SearchResult searchResult) {
        SearchVideoResult.SearchVideoResultBuilder searchVideoResultBuilder = SearchVideoResult.builder();
        searchVideoResultBuilder.source("youtube");
        if (searchResult.getSnippet().getTitle() != null) {
            searchVideoResultBuilder.title(searchResult.getSnippet().getTitle());
        }

        if (searchResult.getId().getVideoId() != null) {
            searchVideoResultBuilder.videoId(searchResult.getId().getVideoId());
        }

        SearchVideoResult searchVideoResult = searchVideoResultBuilder.build();

        ThumbnailDetails thumbnailDetails = searchResult.getSnippet().getThumbnails();
        SearchVideoClipThumbnails.SearchVideoClipThumbnailsBuilder searchVideoClipThumbnailsBuilder =
                SearchVideoClipThumbnails.builder();
        if (thumbnailDetails.getDefault() != null) {
            searchVideoClipThumbnailsBuilder.urlDefault(thumbnailDetails.getDefault().getUrl());
        }

        if (thumbnailDetails.getMedium() != null) {
            searchVideoClipThumbnailsBuilder.urlMedium(thumbnailDetails.getMedium().getUrl());
        }

        if (thumbnailDetails.getHigh() != null) {
            searchVideoClipThumbnailsBuilder.urlHigh(thumbnailDetails.getHigh().getUrl());
        }

        if (thumbnailDetails.getStandard() != null) {
            searchVideoClipThumbnailsBuilder.urlStandard(thumbnailDetails.getStandard().getUrl());
        }

        if (thumbnailDetails.getMaxres() != null) {
            searchVideoClipThumbnailsBuilder.urlMaxRes(thumbnailDetails.getMaxres().getUrl());
        }


        SearchVideoClipThumbnails searchVideoClipThumbnails = searchVideoClipThumbnailsBuilder.build();
        searchVideoResult.setThumbnails(searchVideoClipThumbnails);

        return searchVideoResult;
    }
}
