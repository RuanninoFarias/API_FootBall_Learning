package br.com.meli.Futebol.controllers;

import br.com.meli.Futebol.entities.Ranking;
import br.com.meli.Futebol.services.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ranking")
public class RankingController {

    private final RankingService rankingService;

    @GetMapping
    public ResponseEntity<List<Ranking>> getRanking() {
        List<Ranking> rankings = rankingService.getAllRankings();
        return ResponseEntity.ok(rankings);
    }
}
