package edu.kpi.iasa.mmsa.SpaceX.api.controller;

//import edu.kpi.iasa.mmsa.SpaceX.api.dto.jwt.JwtRequestDto;
//import edu.kpi.iasa.mmsa.SpaceX.api.dto.jwt.JwtResponseDto;
import edu.kpi.iasa.mmsa.SpaceX.api.dto.AccountDto;
import edu.kpi.iasa.mmsa.SpaceX.data.model.Account;
import edu.kpi.iasa.mmsa.SpaceX.exception.UserAlreadyExistsException;
import edu.kpi.iasa.mmsa.SpaceX.service.AccountService;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public final class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> index(){
        return ResponseEntity.ok(accountService.getAccounts());}

    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> show(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }


    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody AccountDto accountDto)
            throws UserAlreadyExistsException{

        final String firstName = accountDto.getFirstName();
        final String lastName = accountDto.getLastName();
        final String email = accountDto.getEmail();
        final String phone = accountDto.getPhone();
        final Long positionId = accountDto.getPositionId();
        final String passwordHash = accountDto.getPasswordHash();
        final Boolean enabled = accountDto.getEnabled();

        final Long id = accountService.createAccount(firstName, lastName, email, phone, positionId, passwordHash, enabled);
        final String location = String.format("/accounts/%d", id);

        return ResponseEntity.created(URI.create(location)).build();


    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @Valid @RequestBody AccountDto accountDto) {
        final String firstName = accountDto.getFirstName();
        final String lastLame = accountDto.getLastName();
        final String email = accountDto.getEmail();
        final String phone = accountDto.getPhone();
        final long positionId = accountDto.getPositionId();
        final String passwordHash = accountDto.getPasswordHash();
        final boolean enabled = accountDto.getEnabled();
        try {
            accountService.updateAccount(id, firstName, lastLame, email, phone, positionId, passwordHash, enabled);
            final String location = String.format("/accounts/%d", id);
            return ResponseEntity.created(URI.create(location)).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        accountService.deleteAccount(id);

        return ResponseEntity.noContent().build();
    }



}
