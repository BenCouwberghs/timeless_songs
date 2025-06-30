package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;

import java.util.List;

public interface SongService {
    void addSong(Song song);
    void modifySong(Song song);
    void deleteSongById(Long id);
    Song fetchSong(Long id);
    List<Song> fetchAllSongs();
    List<Song> fetchAllSongsOfBand(Band band);
}
