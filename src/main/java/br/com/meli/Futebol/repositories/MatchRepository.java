package br.com.meli.Futebol.repositories;

import br.com.meli.Futebol.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findById(Long id);
}
