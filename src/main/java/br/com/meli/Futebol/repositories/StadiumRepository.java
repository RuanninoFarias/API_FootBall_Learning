package br.com.meli.Futebol.repositories;

import br.com.meli.Futebol.entities.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    Optional<Stadium> findById(Long id);
}
