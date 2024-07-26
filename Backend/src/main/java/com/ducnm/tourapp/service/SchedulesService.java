package com.ducnm.tourapp.service;

import com.ducnm.tourapp.dto.Schedules.SchedulesDetailsDto;
import com.ducnm.tourapp.model.Schedules;

import java.util.List;

public interface SchedulesService {
    SchedulesDetailsDto addScheduels(Long dayId, SchedulesDetailsDto schedulesDetailsDto);
    SchedulesDetailsDto editSchedules(Long dayId, SchedulesDetailsDto schedulesDetailsDto);
    List<SchedulesDetailsDto> convertToListDto(List<Schedules> schedulesList);

    void deleteSchedule(Long scheduleId);
}
