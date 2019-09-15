package com.raynigon.raylight.repository;

import com.raynigon.raylight.model.CueMetaData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CueRepository extends JpaRepository<CueMetaData, Integer> {
}
