package com.aincrad.manager.repository;

import com.aincrad.manager.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}