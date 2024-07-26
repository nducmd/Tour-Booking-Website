package com.ducnm.tourapp.dto.DayInPackages;

import com.ducnm.tourapp.dto.Schedules.SchedulesDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DayInPackagesDetailsDto {
    private Long id;
    private String date;
    private String name;
    private Long packagesId;
    private List<SchedulesDetailsDto> schedulesList;
}
