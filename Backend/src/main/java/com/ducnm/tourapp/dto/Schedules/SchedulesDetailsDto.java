package com.ducnm.tourapp.dto.Schedules;

import com.ducnm.tourapp.dto.Destinations.DestinationsDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SchedulesDetailsDto {
    private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private String description;
    private Long daysId;
    private DestinationsDetailsDto des;
}
