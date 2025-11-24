package com.aincrad.manager.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aincrad.manager.model.Player;
import com.aincrad.manager.repository.PlayerRepository;

@Configuration
public class DbSeeder {

    @Bean
    CommandLineRunner initDatabase(PlayerRepository playerRepository) {
        return args -> {
            if (playerRepository.count() == 0) {
                // Cria Kirito (ID 1)
                Player p1 = new Player();
                p1.setNickname("Kirito");
                p1.setLevel(1);
                p1.setCurrentXp(0);
                p1.setXpToNextLevel(100);
                p1.setHp(100); p1.setMaxHp(100); p1.setColCurrency(0);
                playerRepository.save(p1);

                // Cria Asuna (ID 2)
                Player p2 = new Player();
                p2.setNickname("Asuna");
                p2.setLevel(5); // Ela já começa mais forte ;)
                p2.setCurrentXp(0);
                p2.setXpToNextLevel(500);
                p2.setHp(500); p2.setMaxHp(500); p2.setColCurrency(1000);
                playerRepository.save(p2);

                System.out.println("Players Kirito (ID 1) e Asuna (ID 2) criados!");
            }
        };
    }
}
