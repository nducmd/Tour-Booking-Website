package com.ducnm.tourapp.service;

import com.ducnm.tourapp.dto.Cities.CitiesDetailsDto;
import com.ducnm.tourapp.dto.Cities.CitiesSummaryDto;
import com.ducnm.tourapp.model.Cities;

import java.util.List;

public interface CitiesService {
    CitiesSummaryDto addCities(CitiesSummaryDto citiesDto);
    List<CitiesSummaryDto> getAllCities();
    Cities findByName(String city);
    Cities findById(Long id);
    CitiesDetailsDto getCityById(Long id);
}
