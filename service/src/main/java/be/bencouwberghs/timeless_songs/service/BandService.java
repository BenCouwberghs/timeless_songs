package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;

import java.util.List;

public interface BandService {
    void addBand(Band band);
    void modifyBand(Band band);
    void deleteBand(Band band);
    Band fetchBand(Long id);
    List<Band> fetchAllBands();
    Band findBandByName(String name);
}
