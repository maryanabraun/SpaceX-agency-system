package edu.kpi.iasa.mmsa.SpaceX.data.repository;

import edu.kpi.iasa.mmsa.SpaceX.data.model.Spacecraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpacecraftRepository extends JpaRepository<Spacecraft, Long> {
}
