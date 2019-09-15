package com.raynigon.raylight.repository;

import com.raynigon.raylight.model.CueListMetaData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CueListRepository  extends JpaRepository<CueListMetaData, Integer>{

}
