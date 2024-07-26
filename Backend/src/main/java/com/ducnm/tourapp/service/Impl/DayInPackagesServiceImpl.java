package com.ducnm.tourapp.service.Impl;

import com.ducnm.tourapp.dto.DayInPackages.DayInPackagesDetailsDto;
import com.ducnm.tourapp.dto.DayInPackages.DayInPackagesSummaryDto;
import com.ducnm.tourapp.exception.NotFoundException;
import com.ducnm.tourapp.exception.ScheduleException;
import com.ducnm.tourapp.model.DayInPackages;
import com.ducnm.tourapp.model.Packages;
import com.ducnm.tourapp.repository.DayInPackagesRepository;
import com.ducnm.tourapp.repository.PackagesRepository;
import com.ducnm.tourapp.service.ConvertToDtoService;
import com.ducnm.tourapp.service.DayInPackagesService;
import com.ducnm.tourapp.service.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DayInPackagesServiceImpl implements DayInPackagesService {
    @Autowired
    private DayInPackagesRepository dayInPackagesRepository;
    @Autowired
    private PackagesRepository packagesRepository;
    @Autowired
    private ConvertToDtoService toDtoService;
    @Autowired
    private SchedulesService schedulesService;
    @Override
    public DayInPackagesSummaryDto addDayInPackages(Long packageId, DayInPackagesDetailsDto dayInPackagesDetailsDto) {
        Packages packages = packagesRepository.findByIdAndDeletedFalse(packageId).orElse(null);
        if (packages == null) throw new NotFoundException("Không tồn tại gói tour với id " + packageId);
        if (packages.getStartDate().isBefore(LocalDate.now()))
            throw new ScheduleException("Không thể thêm ngày do tour này đã diễn ra");
        LocalDate date = LocalDate.parse(dayInPackagesDetailsDto.getDate());
        if (date.isBefore(packages.getStartDate()) || date.isAfter(packages.getEndDate()))
            throw new ScheduleException("Thời gian phải lớn hơn ngày bắt đầu và nhỏ hơn ngày kết thúc");
        DayInPackages dayInPackages = DayInPackages.builder()
                .date(date)
                .name(dayInPackagesDetailsDto.getName())
                .packages(packages)
                .build();
        return toDtoService.toDayInPackagesSummaryDto(dayInPackagesRepository.save(dayInPackages));
    }

    @Override
    public DayInPackagesSummaryDto editDayInPackages(Long packageId, Long dayId, DayInPackagesDetailsDto dayInPackagesDetailsDto) {
        DayInPackages dayInPackages = dayInPackagesRepository.findById(dayId).orElse(null);
        if (dayInPackages == null) return addDayInPackages(packageId, dayInPackagesDetailsDto);
        Packages packages = dayInPackages.getPackages();
        LocalDate date = LocalDate.parse(dayInPackagesDetailsDto.getDate());
        if (date.isBefore(packages.getStartDate()) || date.isAfter(packages.getEndDate()))
            throw new ScheduleException("Thời gian phải lớn hơn ngày bắt đầu và nhỏ hơn ngày kết thúc");
        dayInPackages.setDate(LocalDate.parse(dayInPackagesDetailsDto.getDate()));
        dayInPackages.setName(dayInPackagesDetailsDto.getName());
        return toDtoService.toDayInPackagesSummaryDto(dayInPackagesRepository.save(dayInPackages));
    }

    @Override
    public void deleteDay(Long id) {
        dayInPackagesRepository.softDeleteById(id);
    }

//    @Override
//    public List<DayInPackagesDetailsDto> convertToListDto(List<DayInPackages> dayInPackages) {
//        if (dayInPackages == null) return null;
//        return dayInPackages.stream()
//                .map(this::convertEntityToDto)
//                .collect(Collectors.toList());
//    }


//    private DayInPackagesDetailsDto convertEntityToDto(DayInPackages dayInPackages) {
//        DayInPackagesDetailsDto dayInPackagesDetailsDto = DayInPackagesDetailsDto.builder()
//                .id(dayInPackages.getId())
//                .name(dayInPackages.getName())
//                .date(dayInPackages.getDate().toString())
//                .packagesId(dayInPackages.getPackages().getId())
//                .schedulesList(schedulesService.convertToListDto(dayInPackages.getSchedulesList()))
//                .build();
//        return dayInPackagesDetailsDto;
//    }
}
