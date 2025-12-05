package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.repository.BandRepository;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BandServiceImplTest {

    @Mock
    private BandRepository bandRepository;

    @Mock
    private MapperEntities mapperEntities;

    @InjectMocks
    private BandServiceImpl bandService;

    @Test
    void fetchAllBands() {
        BandDto band1 = new BandDto() {{
            setId(1L);
            setName("band 1");
            setLinkWikiPage("TestLink1");
            setComments("testComments 1");
        }};

        BandDto band2 = new BandDto() {{
            setId(2L);
            setName("band 2");
            setLinkWikiPage("TestLink2");
            setComments("testComments 2");
        }};

        when(mapperEntities.mapBandEntitiesToDtos(bandRepository.findAllByOrderByNameAsc())).thenReturn(List.of(band1, band2));
        var bandList = bandService.fetchAllBands();

        assertThat(bandList).isNotNull();
        assertThat(bandList.size()).isEqualTo(2);
    }

    @Test
    void addBand() {
        BandDto bandDto3 = new BandDto() {{
            setId(3L);
            setName("band 3");
            setLinkWikiPage("TestLink3");
            setComments("testComments 3");
        }};

        Band band3 = new Band() {{
            setId(3L);
            setName("band 3");
            setLinkWikiPage("TestLink3");
            setComments("testComments 3");
        }};

        when(mapperEntities.mapBandDtoToBandEntity(bandDto3)).thenReturn(band3);
        when(bandRepository.save(band3)).thenReturn(band3);
        bandService.addBand(bandDto3);

        verify(bandRepository).save(band3);
    }

    @Test
    void modifyBand() {
        Band band4 = new Band() {{
            setId(4L);
            setName("band 4");
            setLinkWikiPage("TestLink4");
            setComments("testComments 4");
        }};

        BandDto bandDto4 = new BandDto() {{
            setId(4L);
            setName("band 4");
            setLinkWikiPage("TestLink4");
            setComments("testComments 4");
        }};


        when(bandRepository.findById(bandDto4.getId())).thenReturn(Optional.of(band4));
        when(bandRepository.existsByNameAndIdNot(band4.getName(), band4.getId())).thenReturn(false);
        when(mapperEntities.updateBandEntityFromDto(band4, bandDto4)).thenReturn(band4);

        bandService.modifyBand(bandDto4, band4.getId());

        verify(mapperEntities).updateBandEntityFromDto(band4, bandDto4);
        verify(bandRepository).save(band4);
    }

    @Test
    void deleteBand() {
        Band band5 = new Band() {{
            setId(5L);
            setName("band 5");
            setLinkWikiPage("TestLink5");
            setComments("testComments 5");
        }};

        when(bandRepository.findById(band5.getId())).thenReturn(Optional.of(band5));
        bandService.deleteBandById(band5.getId());

        verify(bandRepository).delete(band5);
    }

    @Test
    void findBandByName() {
        Band band5 = new Band() {{
            setId(5L);
            setName("band 5");
            setLinkWikiPage("TestLink5");
            setComments("testComments 5");
        }};

        when(bandRepository.findByName("band 5")).thenReturn((band5));

        bandService.findBandByName("band 5");

        verify(bandRepository).findByName("band 5");
    }

    @Test
    void getBand() {
        Band band6 = new Band() {{
            setId(6L);
            setName("band 6");
            setLinkWikiPage("TestLink6");
            setComments("testComments 6");
        }};

        Long id = 6L;

        when(bandRepository.getReferenceById(id)).thenReturn(band6);
        bandService.fetchBand(id);

        verify(bandRepository).getReferenceById(id);
    }

    @Test
    void search() {
        Band band6 = new Band() {{
            setId(6L);
            setName("band 6");
            setLinkWikiPage("TestLink6");
            setComments("testComments 6");
        }};

        Band band7 = new Band() {{
            setId(7L);
            setName("band 7");
            setLinkWikiPage("TestLink7");
            setComments("testComments 7");
        }};

        String keyword = "7";

        when(bandRepository.findByNameContainingIgnoreCase(keyword)).thenReturn(List.of(band7));
        bandService.search(keyword);

        verify(bandRepository).findByNameContainingIgnoreCase(keyword);
    }
}