package edu.kpi.iasa.mmsa.SpaceX.api.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
    String code;
    String description;
}
