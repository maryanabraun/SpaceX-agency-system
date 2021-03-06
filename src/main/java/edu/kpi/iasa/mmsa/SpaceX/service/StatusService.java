package edu.kpi.iasa.mmsa.SpaceX.service;

import edu.kpi.iasa.mmsa.SpaceX.data.model.Status;
import edu.kpi.iasa.mmsa.SpaceX.data.repository.StatusRepository;
import edu.kpi.iasa.mmsa.SpaceX.exception.MissionAlreadyExistsException;
import edu.kpi.iasa.mmsa.SpaceX.exception.StatusNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getStatuses(){return statusRepository.findAll();}

    public Status getStatusById(short id) {
        Optional<Status> status = statusRepository.findById(id);
        if (status.isPresent()) {
            log.info("status: {}", status.get());
            return status.get();
        }
        throw new StatusNotFoundException();
    }
    public short createStatus(String status){
        Status newstatus = Status.builder()
                .status(status).build();
        Status saveStatus = statusRepository.save(newstatus);
        return saveStatus.getId();
    }

    public short updateStatus(short id, String status) throws StatusNotFoundException {
        final Optional<Status> maybeStatus = statusRepository.findById(id);
        if(maybeStatus.isEmpty())
            throw new StatusNotFoundException();
        final Status upstatus = maybeStatus.get();
        if (status != null && !status.isBlank()) upstatus.setStatus(status);
        Status saveStatus = statusRepository.save(upstatus);
        return saveStatus.getId();
    }

    public void deleteAccount(short id) {
        statusRepository.deleteById(id);
    }
}
