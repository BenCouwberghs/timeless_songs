package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.dto.GenreDto;
import be.bencouwberghs.timeless_songs.repository.GenreRepository;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;
    private MapperEntities mapperEntities;

    public GenreServiceImpl(GenreRepository genreRepository, MapperEntities mapperEntities) {
        this.genreRepository = genreRepository;
        this.mapperEntities = mapperEntities;
    }

    public GenreDto fetchGenre(Long id) {
        return mapperEntities.mapGenreEntityToGenreDto(genreRepository.getReferenceById(id));
    }

    public List<GenreDto> fetchAllGenres() {
        return mapperEntities.mapGenreEntitiesToDtos(genreRepository.findAll());
    }
}
