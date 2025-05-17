package be.bencouwberghs.timeless_songs;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.repository.BandRepository;
import be.bencouwberghs.timeless_songs.service.BandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class BandIntegrationTest {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private BandService bandService;

    @Test
    void addBand() {
        Band band3 = new Band();

        band3.setName("band 3");
        band3.setLinkWikiPage("testlink3");

        bandService.addBand(band3);

        assertNotNull(bandService.findBandByName("band 3"));
    }

    @Test
    void modifyBand() {
        Band band4 = new Band();

        band4.setName("band 4");
        band4.setLinkWikiPage("testlink4");

        bandService.addBand(band4);

        band4.setName("Beatles");
        bandService.modifyBand(band4);

        assertEquals(band4, bandService.findBandByName("Beatles"));
    }

    @Test
    void deleteBand() {
        Band band5 = new Band();

        band5.setName("band 5");
        band5.setLinkWikiPage("testlink5");

        bandService.addBand(band5);
        bandService.deleteBand(band5);

        assertEquals(0, bandService.fetchAllBands().size());
    }

    @Test
    void fetchAllBands() {
        Band band1 = new Band();

        band1.setName("band 1");
        band1.setLinkWikiPage("testlink1");

        Band band2 = new Band();

        band2.setName("band 2");
        band2.setLinkWikiPage("testlink2");

        bandService.addBand(band1);
        bandService.addBand(band2);

        assertEquals(2, bandService.fetchAllBands().size());
    }
}
