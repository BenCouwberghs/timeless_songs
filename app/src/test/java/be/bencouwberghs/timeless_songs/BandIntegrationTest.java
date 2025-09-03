package be.bencouwberghs.timeless_songs;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.repository.BandRepository;
import be.bencouwberghs.timeless_songs.service.BandService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Import(TestAuditingConfig.class)
public class BandIntegrationTest {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private BandService bandService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void addBand() {
        Band band3 = new Band();

        band3.setName("band 3");
        band3.setLinkWikiPage("testLink3");
        band3.setComments("testComments 3");

        bandService.addBand(band3);

        assertNotNull(bandService.findBandByName("band 3"));
    }

    @Test
    void modifyBand() {
        Band band4 = new Band();

        band4.setName("band 4");
        band4.setLinkWikiPage("testLink4");
        band4.setComments("testComments 4");

        bandService.addBand(band4);

        band4.setName("Beatles");

        entityManager.clear();

        bandService.modifyBand(band4);

        assertEquals(band4, bandService.findBandByName("Beatles"));
    }

    @Test
    void deleteBand() {
        Band band5 = new Band();

        band5.setName("band 5");
        band5.setLinkWikiPage("testLink5");
        band5.setComments("testComments 5");

        bandService.addBand(band5);
        bandService.deleteBandById(band5.getId());

        assertEquals(0, bandService.fetchAllBands().size());
    }

    @Test
    void fetchAllBands() {
        Band band1 = new Band();

        band1.setName("band 1");
        band1.setLinkWikiPage("testLink1");
        band1.setComments("testComments 1");

        Band band2 = new Band();

        band2.setName("band 2");
        band2.setLinkWikiPage("testLink2");
        band2.setComments("testComments 2");

        bandService.addBand(band1);
        bandService.addBand(band2);

        assertEquals(2, bandService.fetchAllBands().size());
    }

    @Test
    void auditBand() {
        Band band = new Band();

        band.setName("band 1");
        band.setLinkWikiPage("testLink1");
        band.setComments("testComments 1");

        bandService.addBand(band);

        band.setName("Beatles");
        bandService.modifyBand(band);

        // got to fetch the updated audit values back from the DB
        // values only update this way when we call directly on the repository and not via the service,
        // despite the method call in the service relying on the repository.
        Band updatedBand = bandRepository.findByName("Beatles");

        assertTrue(updatedBand.getDateLastModified().isAfter(updatedBand.getCreatedDate()));
    }

    @Test
    void getBand() {
        Band band = new Band();

        band.setName("band 1");
        band.setLinkWikiPage("testLink1");
        band.setComments("testComments 1");

        bandService.addBand(band);
        band = bandService.findBandByName("band 1");

        assertEquals(band, bandService.fetchBand(band.getId()));
    }

    @Test
    void search() {
        Band band6 = new Band();

        band6.setName("band 6");
        band6.setLinkWikiPage("testLink6");
        band6.setComments("testComments 6");

        Band band7 = new Band();

        band7.setName("band 7");
        band7.setLinkWikiPage("testLink7");
        band7.setComments("testComments 7");

        bandService.addBand(band6);
        bandService.addBand(band7);

        String keyword = "7";

        assertEquals(1, bandService.search(keyword).size());
    }
}
