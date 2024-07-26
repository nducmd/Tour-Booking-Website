package com.ducnm.tourapp.dto.Schedules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SchedulesSummaryDto {
    private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private String description;
}
