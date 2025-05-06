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
        Band band1 = new Band(1L, "band 1", "Testlink1");
        Band band2 = new Band(2L, "band 2", "Testlink2");

        when(bandRepository.findAll()).thenReturn(List.of(band1, band2));
        var bandList = bandService.fetchAllBands();

        assertThat(bandList).isNotNull();
        assertThat(bandList.size()).isEqualTo(2);
    }

    @Test
    void addBand() {
        Band band3 = new Band(3L, "band 3", "Testlink3");

        bandService.addBand(band3);

        verify(bandRepository).save(band3);
    }

    @Test
    void modifyBand() {
        Band band4 = new Band(4L, "band 4", "Testlink4");

        bandService.modifyBand(band4);

        verify(bandRepository).save(band4);
    }

    @Test
    void deleteBand() {
        Band band5 = new Band(5L, "band 5", "Testlink5");

        bandService.deleteBand(band5);

        verify(bandRepository).delete(band5);
    }
}