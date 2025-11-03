package be.bencouwberghs.timeless_songs.service;

import be.bencouwberghs.timeless_songs.model.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> fetchAllGenres();
}
