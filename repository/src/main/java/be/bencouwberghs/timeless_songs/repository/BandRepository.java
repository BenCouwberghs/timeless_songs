package be.bencouwberghs.timeless_songs.repository;

import be.bencouwberghs.timeless_songs.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    boolean existsByName(String name);

    Band findByName(String name);
}
