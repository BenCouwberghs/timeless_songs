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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
