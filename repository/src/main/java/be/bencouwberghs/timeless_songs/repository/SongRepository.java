package be.bencouwberghs.timeless_songs.repository;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    boolean existsByName(String name);
    Song findByName(String name);
    List<Song> fetchAllSongsOfBand(Band band);
}
