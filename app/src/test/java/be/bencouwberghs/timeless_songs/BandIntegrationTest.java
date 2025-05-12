package be.bencouwberghs.timeless_songs;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.repository.BandRepository;
import be.bencouwberghs.timeless_songs.service.BandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

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

        assertNotNull(band3.getId());
    }
}
