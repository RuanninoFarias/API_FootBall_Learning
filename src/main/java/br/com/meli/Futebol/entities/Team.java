package br.com.meli.Futebol.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "TEAM")
@NoArgsConstructor
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("team")
    @Size(min = 2, message = "The team name must have at least three characters!")
    @Column(name = "TEAM_NAME", nullable = false)
    private String name;

    @JsonProperty("date")
    @Column(name = "DATE_CREATED", nullable = false)
    private String date;

    @JsonProperty("active")
    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

    @JsonProperty("state")
    @Column(name = "STATE", nullable = false)
    @NotNull(message = "State is required")
    private String state;

    @JsonProperty("stadium")
    @OneToOne
    @JoinColumn(name = "STADIUM_ID", nullable = false)
    private Stadium stadium;
}
