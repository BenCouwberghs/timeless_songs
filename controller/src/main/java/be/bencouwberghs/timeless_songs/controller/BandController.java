package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.service.BandService;
import be.bencouwberghs.timeless_songs.service.exception.UserInputException;
import be.bencouwberghs.timeless_songs.service.mapper.MapperEntities;
import be.bencouwberghs.timeless_songs.service.validator.ValidateEntities;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BandController {
    private final BandService bandService;

    private final ValidateEntities validateEntities;

    @PostMapping("/bands")
    public ResponseEntity<BandDto> addBand(@RequestBody BandDto bandDto) {
        validateEntities.validateBand(bandDto);
        Long bandId = bandService.addBand(bandDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bandService.fetchBand(bandId));
    }

    @PatchMapping("/bands/{id}")
    public ResponseEntity<String> modifyBand(@RequestBody BandDto bandDto, @PathVariable Long id) {
        validateEntities.validateBand(bandDto);
        bandService.modifyBand(bandDto, id);
        return ResponseEntity.ok("Successfully updated band.");
    }

    // change service method to deleteBandById and adapt changes here and the tests, same for song.
    @DeleteMapping("/bands/{id}")
    public ResponseEntity<String> deleteBand(@PathVariable Long id) {
        bandService.deleteBandById(id);
        return ResponseEntity.ok("Successfully deleted band.");
    }

    @GetMapping("/bands")
    public List<BandDto> getAllBands() {
        return bandService.fetchAllBands();
    }

    @GetMapping("/bands/{id}")
    public BandDto getBand(@PathVariable Long id) {
        return bandService.fetchBand(id);
    }


    @GetMapping("/bands/search/{searchString}")
    public List<BandDto> search(@PathVariable String searchString) {
        return bandService.search(searchString);
    }

}
