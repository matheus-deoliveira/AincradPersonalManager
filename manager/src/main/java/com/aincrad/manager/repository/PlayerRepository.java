package com.aincrad.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aincrad.manager.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}