package com.example.airport.data.repository;

import com.example.airport.data.model.RunwayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunwayRepository extends JpaRepository<RunwayEntity, Long> {

}
