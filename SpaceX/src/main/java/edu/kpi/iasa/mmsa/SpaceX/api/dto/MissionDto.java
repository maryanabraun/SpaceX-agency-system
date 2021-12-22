package edu.kpi.iasa.mmsa.SpaceX.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class MissionDto {
    private String name;
    private String description;
    private Long customerId;
    private Long spaceCraftId;
    private Short statusId;
    private Long curatorId;
    private Integer payloadWeight;
    private Date date;
    private Integer duration;
    private Byte serviceId;
}
