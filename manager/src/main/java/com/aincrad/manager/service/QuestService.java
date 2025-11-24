package com.aincrad.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aincrad.manager.model.Player;
import com.aincrad.manager.model.Quest;
import com.aincrad.manager.model.QuestStatus;
import com.aincrad.manager.repository.PlayerRepository;
import com.aincrad.manager.repository.QuestRepository;

import jakarta.transaction.Transactional;

@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public List<Quest> findAll() {
        return questRepository.findAll();
    }

    public Quest createQuest(Quest quest, Long playerId) {
        
        Player p = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player não encontrado com ID: " + playerId));
        
        quest.setPlayer(p);
        quest.setStatus(QuestStatus.PENDING);
        
        return questRepository.save(quest);
    }

    public void deleteQuest(Long id) {
        questRepository.deleteById(id);
    }

    @Transactional // Garante que ou salva tudo (XP + Quest) ou não salva nada se der erro
    public Quest completeQuest(Long questId) {
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> new RuntimeException("Quest não encontrada!"));

        if (quest.getStatus() == QuestStatus.COMPLETED) {
            throw new RuntimeException("Esta missão já foi completada e o XP já foi entregue.");
        }

        // Marca a missão como completa
        quest.setStatus(QuestStatus.COMPLETED);

        // Pega o Player dono desta missão
        Player p = quest.getPlayer();

        // Dá as recompensas básicas
        p.setColCurrency(p.getColCurrency() + quest.getColReward());
        p.setCurrentXp(p.getCurrentXp() + quest.getXpReward());

        // Lógica de Level Up (Pode subir múltiplos níveis de uma vez)
        while (p.getCurrentXp() >= p.getXpToNextLevel()) {
            int xpRestante = p.getCurrentXp() - p.getXpToNextLevel();

            // Sobe de nível
            p.setLevel(p.getLevel() + 1);
            p.setCurrentXp(xpRestante);

            // Aumenta a dificuldade do próximo nível (Nível * 100)
            p.setXpToNextLevel(p.getLevel() * 100);

            // Aumenta Status (HP Máximo cresce 10%)
            int novoMaxHp = (int) (p.getMaxHp() * 1.1);
            p.setMaxHp(novoMaxHp);

            // Recupera toda a vida ao passar de nível
            p.setHp(novoMaxHp);

            System.out.println("LEVEL UP! O Jogador " + p.getNickname() + " subiu para o nível " + p.getLevel());
        }

        playerRepository.save(p); // Salva o progresso do Player
        return questRepository.save(quest); // Salva o status da Quest
    }
}
