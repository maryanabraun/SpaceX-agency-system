package edu.kpi.iasa.mmsa.SpaceX.data.repository;

import edu.kpi.iasa.mmsa.SpaceX.data.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmailAndPhone(String email, String phone);
}
