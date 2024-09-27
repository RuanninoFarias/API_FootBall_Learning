package br.com.meli.Futebol.repositories;

import br.com.meli.Futebol.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findById(Long id);
}
