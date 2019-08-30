package com.raynigon.raylight.repository;

import com.raynigon.raylight.model.ControlPanel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlPanelRepository extends JpaRepository<ControlPanel, String> {
}
