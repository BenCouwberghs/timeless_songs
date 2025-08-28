package be.bencouwberghs.timeless_songs.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BandDto {
    private Long id;
    private String name;
    private String linkWikiPage;
}
