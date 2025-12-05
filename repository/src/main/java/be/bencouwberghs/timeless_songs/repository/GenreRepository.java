package be.bencouwberghs.timeless_songs.repository;

import be.bencouwberghs.timeless_songs.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
