package com.ducnm.tourapp.service;

import com.ducnm.tourapp.dto.DayInPackages.DayInPackagesDetailsDto;
import com.ducnm.tourapp.dto.DayInPackages.DayInPackagesSummaryDto;
import com.ducnm.tourapp.model.DayInPackages;

import java.util.List;

public interface DayInPackagesService {
    DayInPackagesSummaryDto addDayInPackages(Long packageId, DayInPackagesDetailsDto dayInPackagesDetailsDto);
    DayInPackagesSummaryDto editDayInPackages(Long packageId, Long dayId, DayInPackagesDetailsDto dayInPackagesDetailsDto);
    void deleteDay(Long id);
}
