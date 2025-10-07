package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;

import java.util.List;

public interface BandService {
    void addBand(Band band);
    void modifyBand(Band band);
    void deleteBandById(Long id);
    Band fetchBand(Long id);
    List<BandDto> fetchAllBands();
    Band findBandByName(String name);
    List<BandDto> search(String keyword);
}
