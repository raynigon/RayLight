package com.raynigon.raylight.repository;

import java.util.List;

import com.raynigon.raylight.model.UniverseMetaData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniverseMetaDataRepository extends JpaRepository<UniverseMetaData, Integer> {

    List<UniverseMetaData> findByOutput(String output);
}
