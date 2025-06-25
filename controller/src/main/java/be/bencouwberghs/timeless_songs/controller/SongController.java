package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.Song;
import be.bencouwberghs.timeless_songs.model.dto.SongDto;
import be.bencouwberghs.timeless_songs.repository.SongRepository;
import be.bencouwberghs.timeless_songs.service.SongService;
import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import be.bencouwberghs.timeless_songs.service.validator.ValidateEntities;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SongController {
    private final SongRepository songRepository;

    private final SongService songService;

    private final MapperEntities mapperEntities;

    private final ValidateEntities validateEntities;

    @PostMapping("/songs")
    public ResponseEntity<String> addSong(@RequestBody SongDto songDto) {
        try {
            validateEntities.validateSong(songDto);
            Song newSong = mapperEntities.mapSongDtoToSongEntity(songDto);
            songService.addSong(newSong);
            return ResponseEntity.ok("Successfully added the song " + songDto.getName());
        } catch (UserInputException userInputException) {
            return ResponseEntity.badRequest().body(userInputException.getMessage());
        }
    }

    @PatchMapping("/songs")
    public ResponseEntity<String> modifySong(@RequestBody SongDto songDto) {
        try {
            validateEntities.validateSong(songDto);
            Song newSong = mapperEntities.mapSongDtoToSongEntity(songDto);
            songService.modifySong(newSong);
            return ResponseEntity.ok("Successfully updated song.");
        } catch (UserInputException userInputException) {
            return ResponseEntity.badRequest().body(userInputException.getMessage());
        }
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        try {
            songService.deleteSong(songRepository.getReferenceById(id));
            return ResponseEntity.ok("Successfully deleted song.");
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/songs")
    public List<Song> getAllSongs() {
        return songService.fetchAllSongs();
    }

    @GetMapping("/songs/{id}")
    public Song getSong(@PathVariable Long id) {
        return songRepository.getReferenceById(id);
    }
}
