package br.com.meli.Futebol.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "MATCH_VS")
@NoArgsConstructor
@Getter
@Setter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("homeTeam")
    @OneToOne
    @JoinColumn(name = "HOME_TEAM_ID", nullable = false)
    private Team homeTeam;

    @JsonProperty("awayTeam")
    @OneToOne
    @JoinColumn(name = "AWAY_TEAM_ID", nullable = false)
    private Team awayTeam;

    @JsonProperty("stadiumMatch")
    @OneToOne
    @JoinColumn(name = "STADIUM_ID", nullable = false)
    private Stadium stadiumMatch;

    @JsonProperty("homeGoals")
    @Column(name = "HOME_TEAM_GOALS", nullable = false)
    private int homeTeamGoals;

    @JsonProperty("awayGoals")
    @Column(name = "AWAY_TEAM_GOALS", nullable = false)
    private int awayTeamGoals;

    @JsonProperty("dateMatch")
    @Column(name = "DATE_TIME_MATCH", nullable = false)
    private String dateHourMatch;

    @JsonProperty("matchName")
    @Column(name = "MATCH_NAME", nullable = false)
    private String matchName;

    @JsonProperty("result")
    @Enumerated(EnumType.STRING)
    @Column(name = "MATCH_RESULT", nullable = false)
    private MatchResult matchResult;

    @PrePersist
    @PreUpdate
    public void calculateMatchDetails() {
        this.matchName = homeTeam.getName() + " X " + awayTeam.getName();

        if (this.homeTeamGoals > this.awayTeamGoals) {
            this.matchResult = MatchResult.HOME_WIN;
        } else if (this.awayTeamGoals > this.homeTeamGoals) {
            this.matchResult = MatchResult.AWAY_WIN;
        } else {
            this.matchResult = MatchResult.DRAW;
        }

        this.stadiumMatch = this.homeTeam.getStadium();
    }
}
