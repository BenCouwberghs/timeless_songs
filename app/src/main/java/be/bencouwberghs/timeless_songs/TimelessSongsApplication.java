package be.bencouwberghs.timeless_songs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TimelessSongsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimelessSongsApplication.class, args);
	}

}
