package be.bencouwberghs.timeless_songs.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BandDto {
    private Long id;
    private String name;
    private String linkWikiPage;
    private String comments;
    @Builder.Default
    @JsonProperty("songs")
    private List<SongDto> songDtos = new ArrayList<>();
    private boolean pinned;
}
