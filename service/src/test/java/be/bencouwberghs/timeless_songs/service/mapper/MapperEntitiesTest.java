package be.bencouwberghs.timeless_songs.service.mapper;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperEntitiesTest {

    MapperEntities mapperEntities = new MapperEntities();

    @Test
    void mapBandEntityToDto() {
        Band band = new Band() {{
            setId(1L);
            setName("band 1");
            setLinkWikiPage("testLink 1");
        }};

        BandDto expected = BandDto.builder()
                .id(1L)
                .name("band 1")
                .wikiLinkPage("testLink 1")
                .build();

        assertEquals(expected, mapperEntities.mapBandEntityToDto(band));
    }

    @Test
    void mapBandDtoToBandEntity() {
        Band expected = new Band() {{
            setId(2L);
            setName("band 2");
            setLinkWikiPage("testLink 2");
        }};

        BandDto bandDto = BandDto.builder()
                .id(2L)
                .name("band 2")
                .wikiLinkPage("testLink 2")
                .build();

        assertEquals(expected, mapperEntities.mapBandDtoToBandEntity(bandDto));
    }
}