package br.com.meli.Futebol.services;

import br.com.meli.Futebol.entities.Stadium;
import br.com.meli.Futebol.entities.Team;
import br.com.meli.Futebol.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {


    private final TeamRepository teamRepository;
    private final StadiumService stadiumService;

    public Team createTeam(Team team, Long id) {
        Stadium stadium = stadiumService.findById(id);
        team.setStadium(stadium);
        return teamRepository.save(team);
    }
    public List<Team> findAllTeam() {
        return teamRepository.findAll();
    }
    public Team findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Team not found!"));
    }
    public Team updateTeam(Long id, Team updatedTeam) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found!"));

        existingTeam.setName(updatedTeam.getName());
        existingTeam.setDate(updatedTeam.getDate());
        existingTeam.setIsActive(updatedTeam.getIsActive());

        return teamRepository.save(existingTeam);
    }
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
