package be.bencouwberghs.timeless_songs.repository.temporary;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.repository.BandRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@AllArgsConstructor
public class DataInitializer {

    private final BandRepository bandRepository;

    @PostConstruct
    public void init() {
        bandRepository.save(Band.builder().name("The Beatles").build());
        bandRepository.save(Band.builder().name("The Rolling Stones").build());
        bandRepository.save(Band.builder().name("Oasis").build());
        bandRepository.save(Band.builder().name("REM").build());
    }
}
