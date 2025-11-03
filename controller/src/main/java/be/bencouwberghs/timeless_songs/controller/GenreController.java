package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.dto.GenreDto;
import be.bencouwberghs.timeless_songs.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genres/{id}")
    public GenreDto getGenre(@PathVariable Long id) {
        return genreService.fetchGenre(id);
    }

    @GetMapping("/genres")
    public List<GenreDto> getAllGenres() {
        return genreService.fetchAllGenres();
    }
}
