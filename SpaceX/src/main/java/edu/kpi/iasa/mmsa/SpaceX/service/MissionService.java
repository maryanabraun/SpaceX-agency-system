package edu.kpi.iasa.mmsa.SpaceX.service;

import edu.kpi.iasa.mmsa.SpaceX.data.model.*;
import edu.kpi.iasa.mmsa.SpaceX.data.repository.*;
import edu.kpi.iasa.mmsa.SpaceX.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final AccountRepository accountRepository;
    private final StatusRepository statusRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final SpacecraftRepository spacecraftRepository;

    public List<Mission> getMissions(){
        return missionRepository.findAll();
    }

    public Mission getMissionById(Long id) {
        Optional<Mission> mission = missionRepository.findById(id);
        if (mission.isPresent()) {
            log.info("mission: {}", mission.get());
            return mission.get();
        }
        throw new MissionNotFoundException();
    }

    public Long createMission(String name, String description, long customerId, long spacecraftId,
                              Short statusId, long curatorId, int payloadWeigh,
                              Date date, int duration, byte serviceTypeId) throws MissionAlreadyExistsException {
        missionRepository.findByName(name).ifPresent(mission -> {throw new MissionAlreadyExistsException();});
        Mission mission = Mission.builder()
                .name(name)
                .description(description)
                .customer(accountRepository.findById(customerId).orElseThrow(UserNotFoundException::new))
                .status(statusRepository.findById(statusId).orElseThrow(StatusNotFoundException::new))
                .spaceCraft(spacecraftRepository.findById(spacecraftId).orElseThrow(CraftNotFoundException::new))
                .curator(accountRepository.findById(curatorId).orElseThrow(UserNotFoundException::new))
                .serviceType(serviceTypeRepository.findById(serviceTypeId).orElseThrow(ServiceNotFoundExeption::new))
                .payloadWeigh(payloadWeigh)
                .date(date)
                .duration(duration).build();
                Mission saveMission = missionRepository.save(mission);
                return saveMission.getId();
    }
    public Long updateMission(long id, String name, String description, Long customerId, Long spacecraftId,
                              Short statusId, Long curatorId, Integer payloadWeigh,
                              Date date, Integer duration, Byte serviceTypeId ) throws MissionNotFoundException {
        final Optional<Mission> maybeMission = missionRepository.findById(id);
        final Mission mission = maybeMission.orElseThrow(MissionNotFoundException::new);
        if (name != null && !name.isBlank()) mission.setName(name);
        if (description != null && !description.isBlank()) mission.setDescription(description);
        if(customerId != null) {
            final Optional<Account> customer = accountRepository.findById(customerId);
            customer.ifPresent(mission::setCustomer);}
        if (curatorId != null) {
            final Optional<Account> curator = accountRepository.findById(curatorId);
            curator.ifPresent(mission::setCurator);
        }
        if (spacecraftId != null) {
            final Optional<Spacecraft> spacecraft = spacecraftRepository.findById(spacecraftId);
            spacecraft.ifPresent(mission::setSpaceCraft);
        }
        if(statusId != null) {
            final Optional<Status> status = statusRepository.findById(statusId);
            status.ifPresent(mission::setStatus);
        }
        if(serviceTypeId != null) {
            final Optional<ServiceType> serviceType = serviceTypeRepository.findById(serviceTypeId);
            serviceType.ifPresent(mission::setServiceType);
        }
        if(payloadWeigh != null) {
            mission.setPayloadWeigh(payloadWeigh);
        }
        if(date != null) {
            mission.setDate(date);
        }
        if(duration != null) {
            mission.setDuration(duration);
        }
        Mission saveMission = missionRepository.save(mission);
        return saveMission.getId();


    }

    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }
}




