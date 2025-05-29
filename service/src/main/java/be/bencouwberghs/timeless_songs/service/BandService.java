package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;

import java.util.List;

public interface BandService {
    void addBand(Band band);
    void addBand(BandDto bandDto);
    void modifyBand(Band band);
    void updateBand(Long id, BandDto bandDto);
    void deleteBand(Band band);
    void deleteBand(Long id);
    List<Band> fetchAllBands();
    Band findBandByName(String name);
}
