package br.com.meli.Futebol.services;

import br.com.meli.Futebol.entities.Match;
import br.com.meli.Futebol.entities.Stadium;
import br.com.meli.Futebol.entities.Team;
import br.com.meli.Futebol.repositories.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final StadiumService stadiumService;
    private final RankingService rankingService;
    private final TeamService teamService;

    public Match createMatch(Match match) {
        Stadium stadium = stadiumService.findById(match.getStadiumMatch().getId());
        Team homeTeam = teamService.findById(match.getHomeTeam().getId());
        Team awayTeam = teamService.findById(match.getAwayTeam().getId());
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setStadiumMatch(stadium);

        Match createdMatch = matchRepository.save(match);
        rankingService.updateRanking(createdMatch);

        return createdMatch;
    }

    public List<Match> findAllMatch() {
        return matchRepository.findAll();
    }

    public Match findById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found!"));
    }

    public Match updateMatch(Long id, Match updatedMatch) {
        Match existingMatch = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found!"));

        // Buscar o estádio pelo ID fornecido no JSON
        Stadium stadium = stadiumService.findById(updatedMatch.getStadiumMatch().getId());
        updatedMatch.setStadiumMatch(stadium);  // Definir o estádio completo na partida

        existingMatch.setHomeTeam(updatedMatch.getHomeTeam());
        existingMatch.setAwayTeam(updatedMatch.getAwayTeam());
        existingMatch.setStadiumMatch(updatedMatch.getStadiumMatch());
        existingMatch.setHomeTeamGoals(updatedMatch.getHomeTeamGoals());
        existingMatch.setAwayTeamGoals(updatedMatch.getAwayTeamGoals());
        existingMatch.setDateHourMatch(updatedMatch.getDateHourMatch());
        existingMatch.setMatchName(updatedMatch.getMatchName());
        existingMatch.setMatchResult(updatedMatch.getMatchResult());

        Match savedMatch = matchRepository.save(existingMatch);
        rankingService.updateRanking(savedMatch);
        return savedMatch;
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
