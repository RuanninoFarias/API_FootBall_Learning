package br.com.meli.Futebol.services;

import br.com.meli.Futebol.entities.Match;
import br.com.meli.Futebol.entities.Ranking;
import br.com.meli.Futebol.entities.Team;
import br.com.meli.Futebol.enums.MatchResult;
import br.com.meli.Futebol.repositories.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

    public void updateRanking(Match match) {
        Team homeTeam = match.getHomeTeam();
        Team awayTeam = match.getAwayTeam();

        Ranking homeRanking = rankingRepository.findByTeam(homeTeam).orElse(new Ranking());
        homeRanking.setTeam(homeTeam);
        updateTeamRanking(homeRanking, match.getHomeTeamGoals(), match.getMatchResult(), true);

        Ranking awayRanking = rankingRepository.findByTeam(awayTeam).orElse(new Ranking());
        awayRanking.setTeam(awayTeam);
        updateTeamRanking(awayRanking, match.getAwayTeamGoals(), match.getMatchResult(), false);

        rankingRepository.save(homeRanking);
        rankingRepository.save(awayRanking);

        updatePositions();
    }

    private void updateTeamRanking(Ranking ranking, Integer teamGoals, MatchResult result, boolean isHomeTeam) {
        int goals = teamGoals != null ? teamGoals : 0;

        ranking.setTotalGames(ranking.getTotalGames() + 1);
        ranking.setTotalGoals(ranking.getTotalGoals() + goals);

        if (result == MatchResult.HOME_WIN && isHomeTeam) {
            ranking.setTotalWins(ranking.getTotalWins() + 1);
            ranking.setTotalPoints(ranking.getTotalPoints() + 3);
        } else if (result == MatchResult.AWAY_WIN && !isHomeTeam) {
            ranking.setTotalWins(ranking.getTotalWins() + 1);
            ranking.setTotalPoints(ranking.getTotalPoints() + 3);
        } else if (result == MatchResult.DRAW) {
            ranking.setTotalDraws(ranking.getTotalDraws() + 1);
            ranking.setTotalPoints(ranking.getTotalPoints() + 1);
        } else {
            ranking.setTotalLosses(ranking.getTotalLosses() + 1);
        }
    }

    public void updatePositions() {
        List<Ranking> allRankings = rankingRepository.findAllByOrderByTotalPointsDesc();

        int position = 1;
        for (Ranking ranking : allRankings) {
            ranking.setPosition(position);
            position++;
        }

        rankingRepository.saveAll(allRankings);
    }

    public List<Ranking> getAllRankings() {
        return rankingRepository.findAllByOrderByTotalPointsDesc();
    }
}
