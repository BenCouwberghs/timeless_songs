package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;

import java.util.List;

public interface BandService {
    void addBand(BandDto bandDto);
    void modifyBand(BandDto bandDto, Long id);
    void deleteBandById(Long id);
    BandDto fetchBand(Long id);
    List<BandDto> fetchAllBands();
    BandDto findBandByName(String name);
    List<BandDto> search(String keyword);
}
