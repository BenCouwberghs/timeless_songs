package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;

import java.util.List;

public interface SongService {
    void addSong(Song song);
    void modifySong(Song song);
    void deleteSong(Song song);
    List<Song> fetchAllSongs();
    List<Song> fetchAllSongsOfBand(Band band);
}
