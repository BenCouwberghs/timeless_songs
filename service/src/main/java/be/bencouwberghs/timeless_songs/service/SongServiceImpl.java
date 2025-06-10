package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.repository.SongRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void addSong(Song song) {
        if (songRepository.existsByName(song.getName())) {
            throw new EntityExistsException("Song name taken: " + song.getName());
        }
        songRepository.save(song);
    }

    public void modifySong(Song song) {
        if (!Objects.equals(songRepository.findByName(song.getName()).getId(), song.getId())) {
            throw new EntityExistsException("Changed song name is already taken: " + song.getName());
        }
        songRepository.save(song);
    }

    public void deleteSong(Song song) {
        songRepository.delete(song);
    }

    public List<Song> fetchAllSongs() {
        return songRepository.findAll();
    }

    public List<Song> fetchAllSongsOfBand(Band band) {
        return songRepository.findAllByBand(band);
    }
}
