package edu.kpi.iasa.mmsa.SpaceX.service;

import edu.kpi.iasa.mmsa.SpaceX.data.model.Position;
import edu.kpi.iasa.mmsa.SpaceX.exception.MissionAlreadyExistsException;
import edu.kpi.iasa.mmsa.SpaceX.exception.PositionNotFoundException;
import edu.kpi.iasa.mmsa.SpaceX.exception.UserAlreadyExistsException;
import edu.kpi.iasa.mmsa.SpaceX.exception.UserNotFoundException;
import edu.kpi.iasa.mmsa.SpaceX.data.model.Account;
import edu.kpi.iasa.mmsa.SpaceX.data.repository.AccountRepository;
import edu.kpi.iasa.mmsa.SpaceX.data.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PositionRepository positionRepository;

    public List<Account> getAccounts(){return accountRepository.findAll();}


    public Long createAccount(String firstName, String lastName,
                                 String email, String phone, Long positionId,
                                 String passwordHash, Boolean enabled ) {
        accountRepository.findByEmailAndPhone(email, phone).ifPresent(account -> {
            throw new UserAlreadyExistsException();
        });

        Account.AccountBuilder builder = Account.builder();
        if (positionId != null) builder = builder.position(positionRepository
                .findById(positionId).orElseThrow(PositionNotFoundException::new));
        if (enabled != null) builder =builder.enabled(enabled);
        Account account = builder
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phone(phone)
                .passwordHash(passwordHash)
                .build();
        Account saveAccount = accountRepository.save(account);
        return saveAccount.getId();
    }

    public Account getAccountById(Long id) {

        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            log.info("user: {}", account.get());
            return account.get();
        }
        throw new UserNotFoundException();
    }

    public Long updateAccount(Long id, String firstName, String lastName,
                                 String email, String phone, long positionId,
                                 String passwordHash, boolean enabled ) throws UserNotFoundException,
            PositionNotFoundException {
        final Optional<Account> maybeAccount = accountRepository.findById(id);
        final Account account = maybeAccount.orElseThrow(UserNotFoundException::new);
        if (firstName != null && !firstName.isBlank()) account.setFirstName(firstName);
        if (lastName != null && !lastName.isBlank()) account.setLastName(lastName);
        if (email != null && !email.isBlank()) account.setEmail(email);
        if (phone!= null && !phone.isBlank()) account.setPhone(phone);
        final Optional<Position> position = positionRepository.findById(positionId);
        if (position.isPresent()) account.setPosition(position.orElseThrow(PositionNotFoundException::new));
        if (passwordHash!= null && !passwordHash.isBlank()) account.setPasswordHash(passwordHash);
        account.setEnabled(enabled);
        Account saveAccount = accountRepository.save(account);
        return saveAccount.getId();


     }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}

