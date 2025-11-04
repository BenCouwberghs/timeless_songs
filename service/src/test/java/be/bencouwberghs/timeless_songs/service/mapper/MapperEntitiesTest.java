package be.bencouwberghs.timeless_songs.service.mapper;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Genre;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.model.dto.GenreDto;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
            setPinned(false);
        }};

        BandDto expected = BandDto.builder()
                .id(1L)
                .name("band 1")
                .linkWikiPage("testLink 1")
                .comments("testComments 1")
                .songDtos(new ArrayList<>())
                .pinned(false)
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
            setPinned(true);
        }};

        BandDto bandDto = BandDto.builder()
                .id(2L)
                .name("band 2")
                .linkWikiPage("testLink 2")
                .comments("testComments 2")
                .pinned(true)
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
            setPinned(false);
        }};

        BandDto bandDto = BandDto.builder()
                .id(3L)
                .name("band 3")
                .linkWikiPage("testLink 3")
                .comments("testComments 3")
                .pinned(false)
                .build();


        Band band = new Band() {{
            setId(3L);
            setName("band 1");
            setLinkWikiPage("testLink 1");
            setComments("testComments 1");
            setPinned(true);
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
            setGenres("1,3");
            setBand(band);
        }};

        SongDto expected = SongDto.builder()
                .id(1L)
                .name("song 1")
                .wikiLinkPage("testLink 1")
                .youTubeClipCode("code 1")
                .genres("1,3")
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
                .genres("5")
                .bandDto(bandDto)
                .build();

        Song expected = new Song() {{
            setId(2L);
            setName("song 2");
            setLinkWikiPage("testLink 2");
            setYouTubeClipCode("code 2");
            setGenres("5");
            setBand(mapperEntities.mapBandDtoToBandEntity(bandDto));
        }};

        assertEquals(expected, mapperEntities.mapSongDtoToSongEntity(songDto));
    }

    @Test
    void mapGenreEntitiesToGenreDtos() {
        Genre genre1 = Genre.builder()
                .id(1L)
                .description("testDescription 1")
                .build();

        Genre genre2 = Genre.builder()
                .id(2L)
                .description("testDescription 2")
                .build();

        GenreDto genreDto1 = GenreDto.builder()
                .id(1L)
                .description("testDescription 1")
                .build();

        GenreDto genreDto2 = GenreDto.builder()
                .id(2L)
                .description("testDescription 2")
                .build();

        assertEquals(List.of(genreDto1, genreDto2), mapperEntities.mapGenreEntitiesToDtos(List.of(genre1, genre2)));

    }
}