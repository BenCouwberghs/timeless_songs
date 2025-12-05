package be.bencouwberghs.timeless_songs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchVideoClipThumbnails {

    private String urlDefault;
    private String urlMedium;
    private String urlHigh;
    private String urlStandard;
    private String urlMaxRes;
}
