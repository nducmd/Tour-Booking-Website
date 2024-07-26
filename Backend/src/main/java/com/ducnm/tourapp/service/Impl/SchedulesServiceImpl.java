package com.ducnm.tourapp.service.Impl;

import com.ducnm.tourapp.dto.Schedules.SchedulesDetailsDto;
import com.ducnm.tourapp.exception.InputException;
import com.ducnm.tourapp.exception.NotFoundException;
import com.ducnm.tourapp.model.DayInPackages;
import com.ducnm.tourapp.model.Destinations;
import com.ducnm.tourapp.model.Schedules;
import com.ducnm.tourapp.repository.DayInPackagesRepository;
import com.ducnm.tourapp.repository.DestinationsRepository;
import com.ducnm.tourapp.repository.SchedulesRepository;
import com.ducnm.tourapp.service.ConvertToDtoService;
import com.ducnm.tourapp.service.DestinationService;
import com.ducnm.tourapp.service.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulesServiceImpl implements SchedulesService {
    @Autowired
    private SchedulesRepository schedulesRepository;
    @Autowired
    private DestinationsRepository destinationsRepository;
    @Autowired
    private DayInPackagesRepository daysRepository;
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private ConvertToDtoService toDtoService;
    @Override
    public SchedulesDetailsDto addScheduels(Long dayId, SchedulesDetailsDto schedulesDetailsDto) {
        DayInPackages dayInPackages = daysRepository.findById(dayId).orElse(null);
        if (dayInPackages == null)
            throw new NotFoundException("Không tồn tại ngày trong tour");
        Destinations destinations = null;
        if (schedulesDetailsDto.getDes() != null && schedulesDetailsDto.getDes().getId() != null) {
            destinations = destinationsRepository.findById(schedulesDetailsDto.getDes().getId()).
                    orElse(null);
        }
        if (schedulesDetailsDto.getEndTime().length() > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime startTime = LocalTime.parse(schedulesDetailsDto.getStartTime(), formatter);
            LocalTime endTime = LocalTime.parse(schedulesDetailsDto.getEndTime(), formatter);
            if (endTime.isBefore(startTime))
                throw new InputException("Giờ kết thúc phải sau giờ bắt đầu");
        }
        Schedules schedules = Schedules.builder()
                .name(schedulesDetailsDto.getName())
                .description(schedulesDetailsDto.getDescription())
                .startTime(schedulesDetailsDto.getStartTime())
                .endTime(schedulesDetailsDto.getEndTime())
                .dayInPackages(dayInPackages)
                .destinations(destinations)
                .build();
        return toDtoService.toSchedulesDetailsDto(schedulesRepository.save(schedules));
    }

    @Override
    public SchedulesDetailsDto editSchedules(Long dayId, SchedulesDetailsDto schedulesDetailsDto) {
        Schedules schedules = schedulesRepository.findById(schedulesDetailsDto.getId()).orElse(null);
        if (schedules == null) return addScheduels(dayId, schedulesDetailsDto);
        Destinations destinations = destinationsRepository.findById(schedulesDetailsDto.getDes().getId()).orElse(null);
        //if (destinations == null) return null;
        schedules.setName(schedulesDetailsDto.getName());
        schedules.setDescription(schedulesDetailsDto.getDescription());
        schedules.setStartTime(schedulesDetailsDto.getStartTime());
        schedules.setEndTime(schedulesDetailsDto.getEndTime());
        schedules.setDestinations(destinations);
        return toDtoService.toSchedulesDetailsDto(schedulesRepository.save(schedules));
    }

    @Override
    public List<SchedulesDetailsDto> convertToListDto(List<Schedules> schedulesList) {
        if (schedulesList == null) return null;
        return toDtoService.toSchedulesDetailsDtoList(schedulesList);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        schedulesRepository.softDeleteById(scheduleId);
    }

//    private SchedulesDetailsDto convertEntityToDto(Schedules schedules) {
//        return SchedulesDetailsDto.builder()
//                .id(schedules.getId())
//                .name(schedules.getName())
//                .description(schedules.getDescription())
//                .startTime(schedules.getStartTime())
//                .endTime(schedules.getEndTime())
//                .daysId(schedules.getDayInPackages().getId())
//                .des(destinationService.convertToDto(schedules.getDestinations()))
//                .build();
//    }
}
