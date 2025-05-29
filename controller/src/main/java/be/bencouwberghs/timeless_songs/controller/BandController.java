package be.bencouwberghs.timeless_songs.controller;

import be.bencouwberghs.timeless_songs.model.Band;
import be.bencouwberghs.timeless_songs.model.dto.BandDto;
import be.bencouwberghs.timeless_songs.service.BandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
public class BandController {
    private final BandService bandService;

    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    // TODO: possibly use Dto with requestBody over a simple string as we only have
    //  1 non nullable property for band right now.
    @PostMapping("/bands")
    public ResponseEntity<String> addBand(String bandName) {
        try {
            Band band = new Band();
            band.setName(bandName);
            bandService.addBand(band);

            return ResponseEntity.ok("Successfully added the band " + bandName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/bands/{id}")
    public ResponseEntity<String> modifyBand(@PathVariable Long id, @RequestBody BandDto bandDto) {
        try {
            bandService.updateBand(id, bandDto);
            return ResponseEntity.ok("Successfully updated band.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/bands/{id}")
    public ResponseEntity<String> deleteBand(@PathVariable Long id) {
        try {
            // function call to delete band
            return ResponseEntity.ok("Successfully deleted band.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
