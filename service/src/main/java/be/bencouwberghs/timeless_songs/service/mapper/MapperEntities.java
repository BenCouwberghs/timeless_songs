package be.bencouwberghs.timeless_songs.service.mapper;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import org.springframework.stereotype.Service;

@Service
public class MapperEntities {
    public BandDto mapBandEntityToDto(Band band) {
        return BandDto.builder()
                .id(band.getId())
                .name(band.getName())
                .wikiLinkPage(band.getLinkWikiPage())
                .build();
    }

    public Band mapBandDtoToBandEntity(BandDto bandDto) {
        return Band.builder()
                .id(bandDto.getId())
                .name(bandDto.getName())
                .linkWikiPage(bandDto.getWikiLinkPage())
                .build();
    }
}
