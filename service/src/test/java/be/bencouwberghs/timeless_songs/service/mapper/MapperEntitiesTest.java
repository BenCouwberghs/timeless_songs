package be.bencouwberghs.timeless_songs.service.mapper;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MapperEntitiesTest {

    MapperEntities mapperEntities = new MapperEntities();

    @Test
    void mapBandEntityToDto() {
        Band band = new Band() {{
            setId(1L);
            setName("band 1");
            setLinkWikiPage("testLink 1");
            setComments("testComments 1");
            setSongs(new ArrayList<>());
        }};

        BandDto expected = BandDto.builder()
                .id(1L)
                .name("band 1")
                .linkWikiPage("testLink 1")
                .comments("testComments 1")
                .songDtos(new ArrayList<>())
                .build();

        assertEquals(expected, mapperEntities.mapBandEntityToDto(band));
    }

    @Test
    void mapBandDtoToBandEntity() {
        Band expected = new Band() {{
            setId(2L);
            setName("band 2");
            setLinkWikiPage("testLink 2");
            setComments("testComments 2");
        }};

        BandDto bandDto = BandDto.builder()
                .id(2L)
                .name("band 2")
                .linkWikiPage("testLink 2")
                .comments("testComments 2")
                .build();

        assertEquals(expected, mapperEntities.mapBandDtoToBandEntity(bandDto));
    }

    @Test
    void updateBandEntityFromDto() {
        Band expected = new Band() {{
            setId(3L);
            setName("band 3");
            setLinkWikiPage("testLink 3");
            setComments("testComments 3");
        }};

        BandDto bandDto = BandDto.builder()
                .id(3L)
                .name("band 3")
                .linkWikiPage("testLink 3")
                .comments("testComments 3")
                .build();


        Band band = new Band() {{
            setId(3L);
            setName("band 1");
            setLinkWikiPage("testLink 1");
            setComments("testComments 1");
        }};

        assertEquals(expected, mapperEntities.updateBandEntityFromDto(band, bandDto));
    }

    @Test
    void mapSongEntityToDto() {
        Band band = Band.builder()
                .id(4L)
                .name("band 4")
                .linkWikiPage("testLink 4")
                .comments("testComments 4")
                .songs(new ArrayList<>())
                .build();

        Song song = new Song() {{
            setId(1L);
            setName("song 1");
            setLinkWikiPage("testLink 1");
            setYouTubeClipCode("code 1");
            setBand(band);
        }};

        SongDto expected = SongDto.builder()
                .id(1L)
                .name("song 1")
                .wikiLinkPage("testLink 1")
                .youTubeClipCode("code 1")
                .bandDto(mapperEntities.mapBandEntityToDto(band))
                .build();

        assertEquals(expected, mapperEntities.mapSongEntityToDto(song));
    }

    @Test
    void mapSongDtoToSongEntity() {
        BandDto bandDto = BandDto.builder()
                .id(5L)
                .name("band 5")
                .linkWikiPage("testLink 5")
                .comments("testComments 5")
                .build();

        SongDto songDto = SongDto.builder()
                .id(2L)
                .name("song 2")
                .wikiLinkPage("testLink 2")
                .youTubeClipCode("code 2")
                .bandDto(bandDto)
                .build();

        Song expected = new Song() {{
            setId(2L);
            setName("song 2");
            setLinkWikiPage("testLink 2");
            setYouTubeClipCode("code 2");
            setBand(mapperEntities.mapBandDtoToBandEntity(bandDto));
        }};

        assertEquals(expected, mapperEntities.mapSongDtoToSongEntity(songDto));
    }
}