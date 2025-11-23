package com.aincrad.manager.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private Integer level;
    private Integer currentXp;
    private Integer xpToNextLevel; // O "teto" para upar

    private Integer hp;
    private Integer maxHp;

    private Integer colCurrency; // O dinheiro do jogo

    // Relacionamento: Se apagar o Player, apaga as Quests dele (Cascade)
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Quest> quests;
}
