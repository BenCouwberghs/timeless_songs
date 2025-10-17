package be.bencouwberghs.timeless_songs;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
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
        BandDto bandDto3 = new BandDto();

        bandDto3.setName("band 3");
        bandDto3.setLinkWikiPage("testLink3");
        bandDto3.setComments("testComments 3");

        bandService.addBand(bandDto3);

        assertNotNull(bandService.findBandByName("band 3"));
    }

    @Test
    void modifyBand() {
        BandDto bandDto4 = new BandDto();

        bandDto4.setName("band 4");
        bandDto4.setLinkWikiPage("testLink4");
        bandDto4.setComments("testComments 4");

        bandService.addBand(bandDto4);
        bandDto4 = bandService.findBandByName("band 4");

        bandDto4.setName("Beatles");

        entityManager.clear();

        bandService.modifyBand(bandDto4, bandDto4.getId());


        assertEquals(bandDto4, bandService.findBandByName("Beatles"));
    }

    @Test
    void deleteBand() {
        BandDto bandDto5 = new BandDto();

        bandDto5.setName("band 5");
        bandDto5.setLinkWikiPage("testLink5");
        bandDto5.setComments("testComments 5");

        bandService.addBand(bandDto5);
        bandDto5 = bandService.findBandByName("band 5");
        bandService.deleteBandById(bandDto5.getId());

        assertEquals(0, bandService.fetchAllBands().size());
    }

    @Test
    void fetchAllBands() {
        BandDto bandDto1 = new BandDto();

        bandDto1.setName("band 1");
        bandDto1.setLinkWikiPage("testLink1");
        bandDto1.setComments("testComments 1");

        BandDto bandDto2 = new BandDto();

        bandDto2.setName("band 2");
        bandDto2.setLinkWikiPage("testLink2");
        bandDto2.setComments("testComments 2");

        bandService.addBand(bandDto1);
        bandService.addBand(bandDto2);

        assertEquals(2, bandService.fetchAllBands().size());
    }

    @Test
    void auditBand() {
        BandDto bandDto = new BandDto();

        bandDto.setName("band 1");
        bandDto.setLinkWikiPage("testLink1");
        bandDto.setComments("testComments 1");

        bandService.addBand(bandDto);
        bandDto = bandService.findBandByName("band 1");

        bandDto.setName("Beatles");
        bandService.modifyBand(bandDto, bandDto.getId());

        // got to fetch the updated audit values back from the DB
        // values only update this way when we call directly on the repository and not via the service,
        // despite the method call in the service relying on the repository.
        Band updatedBand = bandRepository.findByName("Beatles");

        assertTrue(updatedBand.getDateLastModified().isAfter(updatedBand.getCreatedDate()));
    }

    @Test
    void getBand() {
        BandDto bandDto = new BandDto();

        bandDto.setName("band 1");
        bandDto.setLinkWikiPage("testLink1");
        bandDto.setComments("testComments 1");

        bandService.addBand(bandDto);
        bandDto = bandService.findBandByName("band 1");

        assertEquals(bandDto, bandService.fetchBand(bandDto.getId()));
    }

    @Test
    void search() {
        BandDto bandDto6 = new BandDto();

        bandDto6.setName("band 6");
        bandDto6.setLinkWikiPage("testLink6");
        bandDto6.setComments("testComments 6");

        BandDto bandDto7 = new BandDto();

        bandDto7.setName("band 7");
        bandDto7.setLinkWikiPage("testLink7");
        bandDto7.setComments("testComments 7");

        bandService.addBand(bandDto6);
        bandService.addBand(bandDto7);

        String keyword = "7";

        assertEquals(1, bandService.search(keyword).size());
    }
}
