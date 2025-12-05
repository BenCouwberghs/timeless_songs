package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.SearchVideoResult;
import be.bencouwberghs.timeless_songs.service.SearchVideosService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SearchVideosController {
    private final SearchVideosService searchVideosService;

    @GetMapping("/search-videos")
    public List<SearchVideoResult> searchByBandAndSong(@RequestParam String bandName, @RequestParam String songName)
            throws IOException {
        return searchVideosService.searchByBandAndSong(bandName, songName);
    }
}
