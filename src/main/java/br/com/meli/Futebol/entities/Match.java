package br.com.meli.Futebol.entities;

import br.com.meli.Futebol.enums.MatchResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Home team is required")
    @JoinColumn(name = "HOME_TEAM_ID", nullable = false)

    private Team homeTeam;

    @JsonProperty("awayTeam")
    @OneToOne
    @NotNull(message = "Away team is required")
    @JoinColumn(name = "AWAY_TEAM_ID", nullable = false)
    private Team awayTeam;

    @JsonProperty("stadiumMatch")
    @OneToOne
    @NotNull(message = "You need to enter the stadium ID!")
    @JoinColumn(name = "STADIUM_ID", nullable = false)
    private Stadium stadiumMatch;

    @JsonProperty("homeGoals")
    @Min(value = 0, message = "Home team goals must be 0 or greater")
    @Column(name = "HOME_TEAM_GOALS", nullable = false)
    private int homeTeamGoals;

    @JsonProperty("awayGoals")
    @Min(value = 2, message = "Away team goals must be 0 or greater")
    @Column(name = "AWAY_TEAM_GOALS", nullable = false)
    private int awayTeamGoals;

    @JsonProperty("dateMatch")
    @FutureOrPresent(message = "Only dates in the present or future are allowed!")
    @NotNull(message = "Match date is required")
    @Column(name = "DATE_TIME_MATCH", nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
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
