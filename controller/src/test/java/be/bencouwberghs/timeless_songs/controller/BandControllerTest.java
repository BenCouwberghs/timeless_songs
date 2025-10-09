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
    private ValidateEntities validateEntities;

    @Mock
    private BandService bandService;

    @InjectMocks
    private BandController bandController;

    @Test
    void addBand() throws UserInputException {
        BandDto bandDto = BandDto.builder()
                .name("band 1")
                .linkWikiPage("testLink 1")
                .comments("testComments 1")
                .build();

        doNothing().when(validateEntities).validateBand(bandDto);

        bandController.addBand(bandDto);

        verify(bandService).addBand(bandDto);
    }

    @Test
    void modifyBand() throws UserInputException {
        BandDto bandDto2 = BandDto.builder()
                .id(2L)
                .name("band 2")
                .linkWikiPage("testLink 2")
                .comments("testComments 2")
                .build();

        Long id = 2L;

        doNothing().when(validateEntities).validateBand(bandDto2);

        bandController.modifyBand(bandDto2,id);

        verify(bandService).modifyBand(bandDto2);
    }

    @Test
    void deleteBand() {
        Band band3 = new Band() {{
            setId(3L);
            setName("band 3");
            setLinkWikiPage("testLink 3");
            setComments("testComments 3");
        }};

        Long id = 3L;


        bandController.deleteBand(id);

        verify(bandService).deleteBandById(id);
    }

    @Test
    void getAllBands() {
        BandDto bandDto4 = new BandDto() {{
            setName("band 4");
            setLinkWikiPage("testLink 4");
            setComments("testComments 4");
        }};

        BandDto bandDto5 = new BandDto() {{
            setName("band 5");
            setLinkWikiPage("testLink 5");
            setComments("testComments 5");
        }};

        when(bandService.fetchAllBands()).thenReturn(List.of(bandDto4, bandDto5));
        var bandList = bandController.getAllBands();

        assertThat(bandList).isNotNull();
        assertThat(bandList.size()).isEqualTo(2);
    }

    @Test
    void getBand() {
        BandDto bandDto6 = new BandDto() {{
            setId(6L);
            setName("band 6");
            setLinkWikiPage("testLink 6");
            setComments("testComments 6");
        }};

        Long id = 6L;

        when(bandService.fetchBand(id)).thenReturn(bandDto6);
        bandController.getBand(id);

        verify(bandService).fetchBand(id);
    }

    @Test
    void search() {
        BandDto bandDto7 = new BandDto() {{
            setName("Beatles");
            setLinkWikiPage("testLink 7");
            setComments("testComments 7");
        }};

        BandDto bandDto8 = new BandDto() {{
            setName("Sabaton");
            setLinkWikiPage("testLink 8");
            setComments("testComments 8");
        }};

        String searchString = "Beatles";

        when(bandService.search(searchString)).thenReturn(List.of(bandDto7));
        var bandList = bandController.search(searchString);

        assertThat(bandList).isNotNull();
        assertThat(bandList.size()).isEqualTo(1);
    }
}