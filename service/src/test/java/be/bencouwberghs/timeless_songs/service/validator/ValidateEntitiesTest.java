package be.bencouwberghs.timeless_songs.service.validator;

import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateEntitiesTest {

    ValidateEntities validateEntities = new ValidateEntities();

    @Test
    void validateBandFail() {
        BandDto bandDto = BandDto.builder()
                .name("band 1")
                .build();

        assertThrows(UserInputException.class, () -> validateEntities.validateBand(bandDto));
    }

    @Test
    void validateBandPass() {
        BandDto bandDto = BandDto.builder()
                .name("band 2")
                .wikiLinkPage("testLink 2")
                .build();

        assertDoesNotThrow(() -> validateEntities.validateBand(bandDto));
    }

    @Test
    void validateSongFail() {
        SongDto songDto = SongDto.builder()
                .year(1990)
                .build();

        assertThrows(UserInputException.class, () -> validateEntities.validateSong(songDto));
    }

    @Test
    void validateSongPass() {
        SongDto songDto = SongDto.builder()
                .name("song 2")
                .year(1995)
                .wikiLinkPage("testLink 2")
                .build();

        assertDoesNotThrow(() -> validateEntities.validateSong(songDto));
    }
}