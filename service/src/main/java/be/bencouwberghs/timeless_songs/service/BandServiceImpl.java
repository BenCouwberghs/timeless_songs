package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.repository.BandRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class BandServiceImpl implements BandService {
    private final BandRepository bandRepository;

    public BandServiceImpl(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @Override
    public void addBand(Band band) {
        if (bandRepository.existsByName(band.getName())) {
            throw new EntityExistsException("Band name taken: " + band.getName());
        }
        bandRepository.save(band);
    }

    @Override
    public void modifyBand(Band band) {
        if (!Objects.equals(bandRepository.findByName(band.getName()).getId(), band.getId())) {
            throw new EntityExistsException("Changed band name is already taken: " + band.getName());
        }
        bandRepository.save(band);
    }

    @Override
    public void deleteBand(Band band) {
        bandRepository.delete(band);
    }

    @Override
    public List<Band> fetchAllBands() {
        return bandRepository.findAll();
    }

    @Override
    public Band findBandByName(String name) {
        return bandRepository.findByName(name);
    }
}
