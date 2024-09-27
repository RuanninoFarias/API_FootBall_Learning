package br.com.meli.Futebol.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "RANKING")
@NoArgsConstructor
@Getter
@Setter
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", nullable = false)
    private Team team;

    @Column(name = "TOTAL_GAMES", nullable = false)
    private int totalGames;

    @Column(name = "TOTAL_WINS", nullable = false)
    private int totalWins;

    @Column(name = "TOTAL_DRAWS", nullable = false)
    private int totalDraws;

    @Column(name = "TOTAL_LOSSES", nullable = false)
    private int totalLosses;

    @Column(name = "TOTAL_GOALS", nullable = false)
    private int totalGoals;

    @Column(name = "TOTAL_POINTS", nullable = false)
    private int totalPoints;

    @Column(name = "POSITION")
    private int position;
}
