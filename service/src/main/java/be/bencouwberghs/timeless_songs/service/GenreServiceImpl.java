package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.dto.GenreDto;
import be.bencouwberghs.timeless_songs.repository.GenreRepository;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final MapperEntities mapperEntities;

    public GenreServiceImpl(GenreRepository genreRepository, MapperEntities mapperEntities) {
        this.genreRepository = genreRepository;
        this.mapperEntities = mapperEntities;
    }

    public List<GenreDto> fetchAllGenres() {
        return mapperEntities.mapGenreEntitiesToDtos(genreRepository.findAll());
    }
}
