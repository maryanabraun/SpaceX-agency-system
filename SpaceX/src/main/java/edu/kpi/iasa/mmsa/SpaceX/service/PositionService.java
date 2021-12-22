package edu.kpi.iasa.mmsa.SpaceX.service;

//import edu.kpi.iasa.mmsa.SpaceX.exception.PositionNotFoundException;
import edu.kpi.iasa.mmsa.SpaceX.data.model.CraftName;
import edu.kpi.iasa.mmsa.SpaceX.exception.CraftNotFoundException;
import edu.kpi.iasa.mmsa.SpaceX.exception.PositionNotFoundException;
import edu.kpi.iasa.mmsa.SpaceX.data.model.Position;
import edu.kpi.iasa.mmsa.SpaceX.data.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getPositions(){return positionRepository.findAll();}


    public Position getPositionById(long id) {
      Optional<Position> position = positionRepository.findById(id);
      if (position.isPresent()) {
          log.info("position: {}", position.get());
          return position.get();
      }
      throw new PositionNotFoundException();

    }

    public void deletePosition(long id) {
        positionRepository.deleteById(id);
    }
}

