package com.aincrad.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aincrad.manager.model.Quest;
import com.aincrad.manager.service.QuestService;

@RestController
@RequestMapping("/quests")
public class QuestController {
    
    @Autowired
    private QuestService questService;

    @GetMapping
    public List<Quest> getAllQuests() {
        return questService.findAll();
    }
    
    @PostMapping
    public Quest createQuest(@RequestBody Quest quest, @RequestParam Long playerId) {
        return questService.createQuest(quest, playerId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuest(@PathVariable Long id) {
        questService.deleteQuest(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeQuest(@PathVariable Long id) {
        try {
            Quest completedQuest = questService.completeQuest(id);
            return ResponseEntity.ok(completedQuest);
        } catch (RuntimeException e) {
            // Retorna erro 400 (Bad Request) com a mensagem do erro (ex: "Já completada")
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}