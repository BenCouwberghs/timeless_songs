package be.bencouwberghs.timeless_songs.repository;

import be.bencouwberghs.timeless_songs.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);

    Band findByName(String name);
    List<Band> findByNameContainingIgnoreCase(String keyword);
    List<Band> findAllByOrderByNameAsc();
}
