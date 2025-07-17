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

import java.util.List;

@RestController
@RequestMapping("/api")
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

    @PatchMapping("/bands/{id}")
    public ResponseEntity<String> modifyBand(@RequestBody BandDto bandDto, @PathVariable Long id) {
        try {
            validateEntities.validateBand(bandDto);
            Band band = bandService.fetchBand(id);
            band = mapperEntities.updateBandEntityFromDto(band, bandDto);
            bandService.modifyBand(band);
            return ResponseEntity.ok("Successfully updated band.");
        } catch (UserInputException userInputException) {
            return ResponseEntity.badRequest().body(userInputException.getMessage());
        }
    }

    // change service method to deleteBandById and adapt changes here and the tests, same for song.
    @DeleteMapping("/bands/{id}")
    public ResponseEntity<String> deleteBand(@PathVariable Long id) {
        try {
            bandService.deleteBandById(id);
            return ResponseEntity.ok("Successfully deleted band.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/bands")
    public List<Band> getAllBands() {
        return bandService.fetchAllBands();
    }

    @GetMapping("/bands/{id}")
    public BandDto getBand(@PathVariable Long id) {
        return mapperEntities.mapBandEntityToDto(bandService.fetchBand(id));
    }

}
