package com.ducnm.tourapp.dto.Destinations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DestinationStatisticsDto {
    private long count;
    private long fivestar;
    private long fourstar;
    private long free;
}
