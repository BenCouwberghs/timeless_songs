package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.SearchVideoResult;

import java.io.IOException;
import java.util.List;

public interface SearchVideosService {
    List<SearchVideoResult> searchByBandAndSong(String bandName, String songName) throws IOException;
}
