package br.com.meli.Futebol.controllers;

import br.com.meli.Futebol.entities.Team;
import br.com.meli.Futebol.services.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/team")
public class TeamController {
    private final TeamService teamService;

    @PostMapping(value = "/create")
    public ResponseEntity<Team> createTeam(@RequestBody @Valid Team team) {
        if (team.getStadium() == null || team.getStadium().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Team createdTeam = teamService.createTeam(team, team.getStadium().getId());
        return ResponseEntity.created(null).body(createdTeam);
    }
    @GetMapping(value = "")
    public ResponseEntity<List<Team>> getAllTeam() {
        List<Team> allTeam = teamService.findAllTeam();
        return ResponseEntity.ok(allTeam);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Team> getTeamId(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.findById(id));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Team> updateTeam(
            @PathVariable Long id, @Valid @RequestBody Team team) {
        Team updatedTeam = teamService.updateTeam(id, team);
        return ResponseEntity.ok(updatedTeam);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/status/{id}")
    public ResponseEntity<Team> toggleTeamStatus(@PathVariable Long id) {
        Team updatedTeam = teamService.toggleTeamStatus(id);
        return ResponseEntity.ok(updatedTeam);
    }
}
