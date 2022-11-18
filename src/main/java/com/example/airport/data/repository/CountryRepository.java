package com.example.airport.data.repository;

import com.example.airport.data.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    CountryEntity findByCodeOrName(String code, String name);

    CountryEntity findByCode(final String code);
}