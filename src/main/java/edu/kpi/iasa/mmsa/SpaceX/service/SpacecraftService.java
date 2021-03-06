package edu.kpi.iasa.mmsa.SpaceX.service;

import edu.kpi.iasa.mmsa.SpaceX.api.dto.SpacecraftDto;
import edu.kpi.iasa.mmsa.SpaceX.data.model.*;
import edu.kpi.iasa.mmsa.SpaceX.data.repository.CraftNameRepository;
import edu.kpi.iasa.mmsa.SpaceX.data.repository.SpacecraftRepository;
import edu.kpi.iasa.mmsa.SpaceX.exception.CraftNotFoundException;
import edu.kpi.iasa.mmsa.SpaceX.exception.PositionNotFoundException;
import edu.kpi.iasa.mmsa.SpaceX.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class SpacecraftService {
    private final SpacecraftRepository spacecraftRepository;
    private final CraftNameRepository craftNameRepository;

    public List<Spacecraft> getSpacecrafts(){return spacecraftRepository.findAll();}

    public SpacecraftDto spacecraftToDto(Spacecraft spacecraft){
        return SpacecraftDto.builder()
                .capacity(spacecraft.getCapacity())
                .nameId(spacecraft.getCraftName().getId())
                .maxWeight(spacecraft.getMaxWeight())
                .launchPrice(spacecraft.getLaunchPrice()).build();
    }

    public Spacecraft getSpacecraftById(long id) {
        Optional<Spacecraft> spacecraft = spacecraftRepository.findById(id);
        if (spacecraft.isPresent()) {
            log.info("spacecraft: {}", spacecraft.get());
            return spacecraft.get();
        }
        throw new CraftNotFoundException();
    }

    public Long createSpacecraft( byte nameId,
                                  double capacity, int maxWeight, int launchPrice ){
        Spacecraft spacecraft =Spacecraft.builder()
                .craftName(craftNameRepository.findById(nameId).orElseThrow(CraftNotFoundException::new))
                .capacity(capacity)
                .maxWeight(maxWeight)
                .launchPrice(launchPrice).build();
        Spacecraft saveSpacecraft = spacecraftRepository.save(spacecraft);
        return saveSpacecraft.getId();
    }

    public Long updateSpacecraft(long id,  byte nameId,
                                 double capacity, int maxWeight, int launchPrice ) throws IllegalArgumentException,
            CraftNotFoundException {
        final Optional<Spacecraft> maybeSpacecraft = spacecraftRepository.findById(id);
        if (maybeSpacecraft.isEmpty())
            throw new IllegalArgumentException("Invalid spacecraft ID");
        final Spacecraft spacecraft = maybeSpacecraft.get();

        final Optional<CraftName> craftName = craftNameRepository.findById(nameId);

        if (craftName.isPresent()) spacecraft.setCraftName(craftName.orElseThrow(PositionNotFoundException::new));
        spacecraft.setCapacity(capacity);
        spacecraft.setMaxWeight(maxWeight);
        spacecraft.setLaunchPrice(launchPrice);
       Spacecraft saveSpacecraft = spacecraftRepository.save(spacecraft);
        return saveSpacecraft.getId();
    }

    public void deleteSpacecraft(long id) {
        spacecraftRepository.deleteById(id);
    }
}
