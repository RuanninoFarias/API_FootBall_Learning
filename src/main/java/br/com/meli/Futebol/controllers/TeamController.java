package br.com.meli.Futebol.controllers;

import br.com.meli.Futebol.entities.Team;
import br.com.meli.Futebol.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @PostMapping(value = "/team/create")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        if (team.getStadium() == null || team.getStadium().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Team createdTeam = teamService.createTeam(team, team.getStadium().getId());
        return ResponseEntity.created(null).body(createdTeam);
    }
    @GetMapping(value = "/team")
    public ResponseEntity<List<Team>> getAllTeam() {
        List<Team> allTeam = teamService.findAllTeam();
        return ResponseEntity.ok(allTeam);
    }
    @GetMapping(value = "/team/{id}")
    public ResponseEntity<Team> getTeamId(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.findById(id));
    }
    @PutMapping(value = "/team/{id}")
    public ResponseEntity<Team> updateTeam(
            @PathVariable Long id, @RequestBody Team team) {
        Team updatedTeam = teamService.updateTeam(id, team);
        return ResponseEntity.ok(updatedTeam);
    }
    @DeleteMapping(value = "/team/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
