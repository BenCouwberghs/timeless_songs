package be.bencouwberghs.timeless_songs.model.dto;

import be.bencouwberghs.timeless_songs.model.Band;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {
    private Long id;
    private String name;
    private Band band;
    private int year;
    private String wikiLinkPage;
}
