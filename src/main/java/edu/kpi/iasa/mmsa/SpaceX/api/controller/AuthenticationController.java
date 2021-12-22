//package edu.kpi.iasa.mmsa.SpaceX.api.controller;
//
//import edu.kpi.iasa.mmsa.SpaceX.configuration.security.jwt.JwtProcessor;
//import edu.kpi.iasa.mmsa.SpaceX.api.dto.AccountDto;
//import edu.kpi.iasa.mmsa.SpaceX.api.dto.jwt.JwtRequestDto;
//import edu.kpi.iasa.mmsa.SpaceX.api.dto.jwt.JwtResponseDto;
//import edu.kpi.iasa.mmsa.SpaceX.api.dto.RegistrationDto;
//import edu.kpi.iasa.mmsa.SpaceX.modelgenerated.Account;
//import edu.kpi.iasa.mmsa.SpaceX.service.AccountService;
//import edu.kpi.iasa.mmsa.SpaceX.service.PositionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.Collection;
//import java.util.Collections;
//
//@Controller
//public class AuthenticationController {
//
//    private static final String DEFAULT_POSITION = "customer";
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtProcessor jwtProcessor;
//    private final UserDetailsService userDetailsService;
//    private final AccountService accountService;
//    private final PasswordEncoder passwordEncoder;
//    private final PositionService positionService;
//
//    @Autowired
//    public AuthenticationController(AuthenticationManager authenticationManager,
//                                    JwtProcessor jwtProcessor,
//                                    UserDetailsService userDetailsService,
//                                    AccountService accountService,
//                                    PasswordEncoder passwordEncoder,
//                                    PositionService positionService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtProcessor = jwtProcessor;
//        this.userDetailsService = userDetailsService;
//        this.accountService = accountService;
//        this.passwordEncoder = passwordEncoder;
//        this.positionService = positionService;
//    }
//
//    @PostMapping("/signin")
//    public ResponseEntity<JwtResponseDto> signIn(@RequestBody JwtRequestDto jwtRequestDto) {
//        String username = jwtRequestDto.getEmail();
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, jwtRequestDto.getPassword()));
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        String token = jwtProcessor.createJwt(username,
//                (Collection<GrantedAuthority>) userDetails.getAuthorities());
//        return ResponseEntity.ok(new JwtResponseDto(token));
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<AccountDto> signUp(@RequestBody RegistrationDto registrationDto) {
//        Account account = createAccount(registrationDto);
//        return ResponseEntity.ok(createAccountDto(accountService.saveAccount(account)));
//    }
//
//    private AccountDto createAccountDto(Account account) {
//        return new AccountDto(account.getEmail());
//    }
//
//    private Account createAccount(RegistrationDto registrationDto) {
//        Account account = Account.builder()
//                .email(registrationDto.getEmail())
//                .passwordHash(passwordEncoder.encode(registrationDto.getPassword()))
//                .firstName(registrationDto.getFirstName())
//                .lastName(registrationDto.getLastName())
//                .phone(registrationDto.getPhone())
//                .positionId(registrationDto.getPositionId())
//                .build();
//        account.setPositionId(Collections.singleton(positionService.getPositionById(DEFAULT_POSITION)));
//        return account;
//    }
//}
