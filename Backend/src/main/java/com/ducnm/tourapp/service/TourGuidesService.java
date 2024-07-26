package com.ducnm.tourapp.service;

import com.ducnm.tourapp.auth.Token;
import com.ducnm.tourapp.dto.Packages.PackagesBasicDto;
import com.ducnm.tourapp.dto.Packages.PackagesDetailsDto;
import com.ducnm.tourapp.dto.TourGuides.TourGuidesDetailsDto;
import com.ducnm.tourapp.dto.TourGuides.TourGuidesStatisticsDto;
import com.ducnm.tourapp.dto.TourGuides.TourGuidesSummaryDto;
import com.ducnm.tourapp.model.TourGuides;
import com.ducnm.tourapp.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TourGuidesService {

    TourGuides addTourGuides(TourGuidesDetailsDto tourGuidesDetailsDto, User user);
    TourGuidesDetailsDto getTourGuidesDtoByToken(Token token);
    TourGuidesDetailsDto editTourGuidesInfo(Token token, TourGuidesDetailsDto newTourGuides);
    List<TourGuidesSummaryDto> getAllTourGuides();
    TourGuidesSummaryDto getTourGuideById(Long id);
    TourGuidesDetailsDto editTourGuideSalary(TourGuidesDetailsDto newTourGuides);
    TourGuidesDetailsDto convertToDtoForPackage(TourGuides tourGuides);
    List<PackagesBasicDto> getPackagesList(Token token);
    TourGuidesStatisticsDto getTourGuidesStatistics();
    Page<TourGuidesSummaryDto> searchTourguides(String keyword, int page);
}
