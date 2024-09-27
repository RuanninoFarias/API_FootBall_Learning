package br.com.meli.Futebol.controllers;

import br.com.meli.Futebol.entities.Stadium;
import br.com.meli.Futebol.services.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stadium")
public class StadiumController {

    private final StadiumService stadiumService;

    @PostMapping(value = "/create")
    public ResponseEntity<Stadium> createStadium(@RequestBody Stadium stadium) {
        Stadium createdStadium = stadiumService.createStadium(stadium);
        return ResponseEntity.ok(createdStadium);
    }
    @GetMapping
    public ResponseEntity<List<Stadium>> getAllStadium() {
        List<Stadium> allStadium= stadiumService.findAllStadium();
        return ResponseEntity.ok(allStadium);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Stadium> getStadiumId(@PathVariable Long id) {
        return ResponseEntity.ok(stadiumService.findById(id));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Stadium> updateStadium(
            @PathVariable Long id, @RequestBody Stadium stadium) {
        Stadium updatedStadium = stadiumService.updateStadium(id, stadium);
        return ResponseEntity.ok(updatedStadium);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteStadium(@PathVariable Long id) {
        stadiumService.deleteStadium(id);
        return ResponseEntity.noContent().build();
    }
}
