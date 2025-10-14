package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;

import java.util.List;

public interface SongService {
    void addSong(SongDto songDto);
    void modifySong(SongDto songDto, Long id);
    void deleteSongById(Long id);
    SongDto fetchSong(Long id);
    List<SongDto> fetchAllSongs();
    List<SongDto> fetchAllSongsOfBand(Long bandId);
    SongDto findSongByName(String name);
}
