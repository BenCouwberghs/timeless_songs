package be.bencouwberghs.timeless_songs.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchVideoResult {

    private String source;
    private String title;
    private String videoId;
    private SearchVideoClipThumbnails thumbnails;
}
