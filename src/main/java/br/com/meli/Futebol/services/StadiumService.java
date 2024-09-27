package br.com.meli.Futebol.services;

import br.com.meli.Futebol.entities.Stadium;
import br.com.meli.Futebol.repositories.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository stadiumRepository;

    public Stadium createStadium(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }
    public List<Stadium> findAllStadium() {
        return stadiumRepository.findAll();
    }
    public Stadium findById(Long id) {
        return stadiumRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Stadium not found!"));
    }
    public Stadium updateStadium(Long id, Stadium updatedStadium) {
        Stadium existingStadium = stadiumRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Stadium not found!"));
        existingStadium.setStadiumName(updatedStadium.getStadiumName());

        return stadiumRepository.save(updatedStadium);
    }
    public void deleteStadium(Long id) {
        stadiumRepository.deleteById(id);
    }
}
