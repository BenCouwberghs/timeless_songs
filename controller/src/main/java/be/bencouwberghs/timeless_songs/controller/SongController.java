package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import be.bencouwberghs.timeless_songs.service.SongService;
import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
import be.bencouwberghs.timeless_songs.service.validator.ValidateEntities;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SongController {
    private final SongService songService;

    private final ValidateEntities validateEntities;

    @PostMapping("/songs")
    public ResponseEntity<String> addSong(@RequestBody SongDto songDto) {
        validateEntities.validateSong(songDto);
        songService.addSong(songDto);
        return ResponseEntity.ok("Successfully added the song " + songDto.getName());
    }

    @PatchMapping("/songs/{id}")
    public ResponseEntity<String> modifySong(@RequestBody SongDto songDto, @PathVariable Long id) {
        validateEntities.validateSong(songDto);
        songService.modifySong(songDto, id);
        return ResponseEntity.ok("Successfully updated song.");
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        songService.deleteSongById(id);
        return ResponseEntity.ok("Successfully deleted song.");
    }

    @GetMapping("/songs")
    public List<SongDto> getAllSongs() {
        return songService.fetchAllSongs();
    }

    @GetMapping("/songs/{id}")
    public SongDto getSong(@PathVariable Long id) {
        return songService.fetchSong(id);
    }
}
