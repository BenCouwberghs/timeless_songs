package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.repository.BandRepository;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandServiceImpl implements BandService {
    private final BandRepository bandRepository;
    private final MapperEntities mapperEntities;

    public BandServiceImpl(BandRepository bandRepository, MapperEntities mapperEntities) {
        this.bandRepository = bandRepository;
        this.mapperEntities = mapperEntities;
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
        bandRepository.delete(mapperEntities.mapBandDtoToBandEntity(fetchBand(id)));
    }

    public BandDto fetchBand(Long id) {
        return mapperEntities.mapBandEntityToDto(bandRepository.getReferenceById(id));
    }


    public List<BandDto> fetchAllBands() {
        return mapperEntities.mapBandEntitiesToDtos(
                bandRepository.findAllByOrderByNameAsc());
    }


    public Band findBandByName(String name) {
        return bandRepository.findByName(name);
    }

    public List<BandDto> search(String keyword) {
        return mapperEntities.mapBandEntitiesToDtos(
                bandRepository.findByNameContainingIgnoreCase(keyword));
    }
}
