package be.bencouwberghs.timeless_songs.model.dto;

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
    private BandDto bandDto;
    private int year;
    private String wikiLinkPage;
}
