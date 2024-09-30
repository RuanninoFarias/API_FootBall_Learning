package br.com.meli.Futebol.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "STADIUM")
@NoArgsConstructor
@Getter
@Setter
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(value = "stadiumName")
    @Column(name = "STADIUM_NAME", nullable = false)
    @NotBlank(message = "To register a stadium it needs to have a name!")
    private String stadiumName;
}
