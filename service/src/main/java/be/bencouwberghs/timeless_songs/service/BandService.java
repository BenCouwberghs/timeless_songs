package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;

import java.util.List;

public interface BandService {
    void addBand(Band band);
    Band modifyBand(Band band);
    void deleteBand(Band band);
    List<Band> fetchAllBands();
}
