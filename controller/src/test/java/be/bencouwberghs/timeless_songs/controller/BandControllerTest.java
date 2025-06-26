package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.service.BandService;
import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import be.bencouwberghs.timeless_songs.service.validator.ValidateEntities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BandControllerTest {

    @Mock
    private MapperEntities mapperEntities;

    @Mock
    private ValidateEntities validateEntities;

    @Mock
    private BandService bandService;

    @InjectMocks
    private BandController bandController;

    @Test
    void addBand() throws UserInputException {
        BandDto bandDto = BandDto.builder()
                .name("band 1")
                .wikiLinkPage("testLink 1")
                .build();

        Band band = new Band() {{
            setName("band 1");
            setLinkWikiPage("testLink 1");
        }};

        doNothing().when(validateEntities).validateBand(bandDto);
        when(mapperEntities.mapBandDtoToBandEntity(bandDto)).thenReturn(band);

        bandController.addBand(bandDto);

        verify(bandService).addBand(band);
    }

    @Test
    void modifyBand() throws UserInputException {
        BandDto bandDto2 = BandDto.builder()
                .name("band 2")
                .wikiLinkPage("testLink 2")
                .build();

        Band band2 = new Band() {{
            setName("band 2");
            setLinkWikiPage("testLink 2");
        }};

        doNothing().when(validateEntities).validateBand(bandDto2);
        when(mapperEntities.mapBandDtoToBandEntity(bandDto2)).thenReturn(band2);

        bandController.modifyBand(bandDto2);

        verify(bandService).modifyBand(band2);
    }

    @Test
    void deleteBand() {
        Band band3 = new Band() {{
            setId(3L);
            setName("band 3");
            setLinkWikiPage("testLink 3");
        }};

        Long id = 3L;

        when(bandService.fetchBand(id)).thenReturn(band3);
        bandController.deleteBand(id);

        verify(bandService).deleteBand(band3);
    }

    @Test
    void getAllBands() {
        Band band4 = new Band() {{
            setName("band 4");
            setLinkWikiPage("testLink 4");
        }};

        Band band5 = new Band() {{
            setName("band 5");
            setLinkWikiPage("testLink 5");
        }};

        when(bandService.fetchAllBands()).thenReturn(List.of(band4, band5));
        var bandList = bandController.getAllBands();

        assertThat(bandList).isNotNull();
        assertThat(bandList.size()).isEqualTo(2);
    }

    @Test
    void getBand() {
        Band band6 = new Band() {{
            setId(6L);
            setName("band 6");
            setLinkWikiPage("testLink 6");
        }};

        Long id = 6L;

        when(bandService.fetchBand(id)).thenReturn(band6);
        bandController.getBand(id);

        verify(bandService).fetchBand(id);
    }
}