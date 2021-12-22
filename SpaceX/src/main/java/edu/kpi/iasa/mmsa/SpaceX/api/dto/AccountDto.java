package edu.kpi.iasa.mmsa.SpaceX.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class AccountDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long positionId;
    private String passwordHash;
    private Boolean enabled;


}