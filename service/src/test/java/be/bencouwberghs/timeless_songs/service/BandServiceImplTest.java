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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

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

        given(bandRepository.findAll()).willReturn(List.of(band1, band2));
        var bandList = bandService.fetchAllBands();

        assertThat(bandList).isNotNull();
        assertThat(bandList.size()).isEqualTo(2);
    }
}