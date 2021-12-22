package edu.kpi.iasa.mmsa.SpaceX.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegistrationDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private int positionId;
}
