package br.com.meli.Futebol.entities;

import jakarta.persistence.*;
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

    @Column(name = "STADIUM_NAME", nullable = false)
    private String stadiumName;
}
