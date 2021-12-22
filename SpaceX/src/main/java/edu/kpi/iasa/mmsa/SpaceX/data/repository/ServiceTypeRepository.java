package edu.kpi.iasa.mmsa.SpaceX.data.repository;


import edu.kpi.iasa.mmsa.SpaceX.data.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Byte> {

}
