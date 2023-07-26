package com.app.animalTracker.repository;

import com.app.animalTracker.models.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SightingsRepository  extends JpaRepository<Sighting, Long> {
}
