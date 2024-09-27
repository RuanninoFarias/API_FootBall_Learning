package br.com.meli.Futebol.repositories;

import br.com.meli.Futebol.entities.Ranking;
import br.com.meli.Futebol.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Optional<Ranking> findByTeam(Team team);

    List<Ranking> findAllByOrderByTotalPointsDesc();
}
