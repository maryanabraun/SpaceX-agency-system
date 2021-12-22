package edu.kpi.iasa.mmsa.SpaceX.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class RedisDto {
    String key;
    String value;
}
