package edu.kpi.iasa.mmsa.SpaceX.data.repository;

import edu.kpi.iasa.mmsa.SpaceX.data.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

}