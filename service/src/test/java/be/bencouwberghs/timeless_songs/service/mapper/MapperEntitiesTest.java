package be.bencouwberghs.timeless_songs.service.mapper;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
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

    @Test
    void mapSongEntityToDto() {
        Song song = new Song() {{
            setId(1L);
            setName("song 1");
            setLinkWikiPage("testLink 1");
        }};

        SongDto expected = SongDto.builder()
                .id(1L)
                .name("song 1")
                .wikiLinkPage("testLink 1")
                .build();

        assertEquals(expected, mapperEntities.mapSongEntityToDto(song));
    }

    @Test
    void mapSongDtoToSongEntity() {
        Song expected = new Song() {{
            setId(2L);
            setName("song 2");
            setLinkWikiPage("testLink 2");
        }};

        SongDto songDto = SongDto.builder()
                .id(2L)
                .name("song 2")
                .wikiLinkPage("testLink 2")
                .build();

        assertEquals(expected, mapperEntities.mapSongDtoToSongEntity(songDto));
    }
}