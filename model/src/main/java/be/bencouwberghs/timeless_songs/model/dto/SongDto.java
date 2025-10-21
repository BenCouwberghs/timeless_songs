package be.bencouwberghs.timeless_songs.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("band")
    private BandDto bandDto;
    private int year;
    private String wikiLinkPage;
}
