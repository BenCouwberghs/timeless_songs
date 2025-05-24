package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.repository.BandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BandServiceImplTest {

    @Mock
    private BandRepository bandRepository;

    @InjectMocks
    private BandServiceImpl bandService;

    @Test
    void fetchAllBands() {
        Band band1 = new Band() {{
            setId(1L);
            setName("band 1");
            setLinkWikiPage("Testlink1");
        }};

        Band band2 = new Band() {{
            setId(2L);
            setName("band 2");
            setLinkWikiPage("Testlink2");
        }};

        when(bandRepository.findAll()).thenReturn(List.of(band1, band2));
        var bandList = bandService.fetchAllBands();

        assertThat(bandList).isNotNull();
        assertThat(bandList.size()).isEqualTo(2);
    }

    @Test
    void addBand() {
        Band band3 = new Band() {{
            setId(3L);
            setName("band 3");
            setLinkWikiPage("Testlink3");
        }};

        bandService.addBand(band3);

        verify(bandRepository).save(band3);
    }

    @Test
    void modifyBand() {
        Band band4 = new Band() {{
            setId(4L);
            setName("band 4");
            setLinkWikiPage("Testlink4");
        }};

        bandService.modifyBand(band4);

        verify(bandRepository).save(band4);
    }

    @Test
    void deleteBand() {
        Band band5 = new Band() {{
            setId(5L);
            setName("band 5");
            setLinkWikiPage("Testlink5");
        }};

        bandService.deleteBand(band5);

        verify(bandRepository).delete(band5);
    }

    @Test
    void findBandByName() {
        Band band5 = new Band() {{
            setId(5L);
            setName("band 5");
            setLinkWikiPage("Testlink5");
        }};


        when(bandRepository.findByName("band 5")).thenReturn(band5);

        assertThat(band5).isEqualTo(bandService.findBandByName("band 5"));
    }
}