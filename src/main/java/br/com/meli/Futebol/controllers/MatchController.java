package br.com.meli.Futebol.controllers;

import br.com.meli.Futebol.entities.Match;
import br.com.meli.Futebol.services.MatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/create")
    public ResponseEntity<Match> createMatch(@RequestBody @Valid Match match) {
        Match createdMatch = matchService.createMatch(match);
        return ResponseEntity.ok(createdMatch);
    }

    @GetMapping("")
    public ResponseEntity<List<Match>> getAllMatch() {
        List<Match> allMatch = matchService.findAllMatch();
        return ResponseEntity.ok(allMatch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @Valid @RequestBody Match match) {
        Match updatedMatch = matchService.updateMatch(id, match);
        return ResponseEntity.ok(updatedMatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
