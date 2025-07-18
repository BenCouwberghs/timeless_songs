package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.repository.BandRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BandServiceImpl implements BandService {
    private final BandRepository bandRepository;

    public BandServiceImpl(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }


    public void addBand(Band band) {
        if (bandRepository.existsByName(band.getName())) {
            throw new EntityExistsException("Band name taken: " + band.getName());
        }
        bandRepository.save(band);
    }


    public void modifyBand(Band band) {
        if (bandRepository.existsByNameAndIdNot(band.getName(), band.getId())) {
            throw new EntityExistsException("Changed band name is already taken: " + band.getName());
        }
        bandRepository.save(band);
    }

    // TODO: Need to make it so to check if band still has a song and if that's the case throw an exception.


    public void deleteBandById(Long id) {
        bandRepository.delete(fetchBand(id));
    }

    public Band fetchBand(Long id) {
        return bandRepository.getReferenceById(id);
    }


    public List<Band> fetchAllBands() {
        return bandRepository.findAllByOrderByNameAsc();
    }


    public Band findBandByName(String name) {
        return bandRepository.findByName(name);
    }
}
