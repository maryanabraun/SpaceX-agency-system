package edu.kpi.iasa.mmsa.SpaceX.api.controller;


import edu.kpi.iasa.mmsa.SpaceX.api.dto.MissionDto;
import edu.kpi.iasa.mmsa.SpaceX.data.model.Mission;
import edu.kpi.iasa.mmsa.SpaceX.data.repository.MissionRepository;
import edu.kpi.iasa.mmsa.SpaceX.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;
@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/missions")
public class MissionController {
    private final MissionService missionService;
    private final MissionRepository missionRepository;
    @GetMapping
    public ResponseEntity<List<Mission>> index(){
        return ResponseEntity.ok(missionService.getMissions());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Mission> show(@PathVariable Long id) {
        return ResponseEntity.ok(missionService.getMissionById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody MissionDto missionDto){
        final String name = missionDto.getName();
        final String description = missionDto.getDescription();
        final long customerId = missionDto.getCustomerId();
        final long spacecraftId = missionDto.getSpaceCraftId();
        final Short statusId = missionDto.getStatusId();
        final long curatorId = missionDto.getCuratorId();
        final int payloadWeigh = missionDto.getPayloadWeight();
        final Date date = missionDto.getDate();
        final int duration = missionDto.getDuration();
        final byte serviceTypeId = missionDto.getServiceId();
        final long id = missionService.createMission(name, description, customerId, spacecraftId, statusId, curatorId,
                payloadWeigh, date, duration, serviceTypeId);
        final String location = String.format("/missions/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> change(@PathVariable Long id, @Valid @RequestBody MissionDto missionDto) {
        final String name = missionDto.getName();
        final String description = missionDto.getDescription();
        final Long customerId = missionDto.getCustomerId();
        final Long spacecraftId = missionDto.getSpaceCraftId();
        final Short statusId = missionDto.getStatusId();
        final Long curatorId = missionDto.getCuratorId();
        final Integer payloadWeigh = missionDto.getPayloadWeight();
        final Date date = missionDto.getDate();
        final Integer duration = missionDto.getDuration();
        final Byte serviceTypeId = missionDto.getServiceId();
        try {
            missionService.updateMission(id, name, description, customerId, spacecraftId, statusId, curatorId,
                    payloadWeigh, date, duration, serviceTypeId);
            final String location = String.format("/missions/%d", id);
            return ResponseEntity.created(URI.create(location)).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        missionService.deleteMission(id);

        return ResponseEntity.noContent().build();
    }

}
