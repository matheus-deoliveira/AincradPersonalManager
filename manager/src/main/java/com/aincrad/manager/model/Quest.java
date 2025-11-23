package com.aincrad.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING) // Salva "S_RANK" no banco em vez de números 0,1,2...
    private QuestDifficulty difficulty;

    @Enumerated(EnumType.STRING)
    private QuestStatus status;

    private Integer xpReward;
    private Integer colReward;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonIgnore // Importante: Evita loop infinito ao converter para JSON (Player -> Quest -> Player...)
    private Player player;
}
