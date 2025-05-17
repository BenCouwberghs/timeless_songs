package be.bencouwberghs.timeless_songs.repository;

import be.bencouwberghs.timeless_songs.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
    boolean existsByName(String name);
    Song findByName(String name);
}
