package com.aincrad.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aincrad.manager.model.Player;
import com.aincrad.manager.model.Quest;
import com.aincrad.manager.model.QuestStatus;
import com.aincrad.manager.repository.PlayerRepository;
import com.aincrad.manager.repository.QuestRepository;

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
}
