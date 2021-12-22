package edu.kpi.iasa.mmsa.SpaceX.data.repository;

import edu.kpi.iasa.mmsa.SpaceX.data.model.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    Optional<Mission> findByName(String name);
}
