package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.service.BandService;
import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import be.bencouwberghs.timeless_songs.service.validator.ValidateEntities;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@AllArgsConstructor
public class BandController {
    private final BandService bandService;

    private final MapperEntities mapperEntities;

    private final ValidateEntities validateEntities;

    @PostMapping("/bands")
    public ResponseEntity<String> addBand(@RequestBody BandDto bandDto) {
        try {
            validateEntities.validateBand(bandDto);
            Band newBand = mapperEntities.mapBandDtoToBandEntity(bandDto);
            bandService.addBand(newBand);
            return ResponseEntity.ok("Successfully added the band " + bandDto.getName());
        } catch (UserInputException userInputException) {
            return ResponseEntity.badRequest().body(userInputException.getMessage());
        }
    }

    @PatchMapping("/bands")
    public ResponseEntity<String> modifyBand(@RequestBody BandDto bandDto) {
        try {
            validateEntities.validateBand(bandDto);
            Band newBand = mapperEntities.mapBandDtoToBandEntity(bandDto);
            bandService.modifyBand(newBand);
            return ResponseEntity.ok("Successfully updated band.");
        } catch (UserInputException userInputException) {
            return ResponseEntity.badRequest().body(userInputException.getMessage());
        }
    }

    @DeleteMapping("/bands")
    public ResponseEntity<String> deleteBand(@RequestBody BandDto bandDto) {
        try {
            validateEntities.validateBand(bandDto);
            Band newBand = mapperEntities.mapBandDtoToBandEntity(bandDto);
            bandService.deleteBand(newBand);
            return ResponseEntity.ok("Successfully deleted band.");
        } catch (UserInputException userInputException) {
            return ResponseEntity.badRequest().body(userInputException.getMessage());
        }
    }

}
