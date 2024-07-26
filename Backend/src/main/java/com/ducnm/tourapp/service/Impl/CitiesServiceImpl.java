package com.ducnm.tourapp.service.Impl;

import com.ducnm.tourapp.dto.Cities.CitiesDetailsDto;
import com.ducnm.tourapp.dto.Cities.CitiesSummaryDto;
import com.ducnm.tourapp.model.Cities;
import com.ducnm.tourapp.repository.CitiesRepository;
import com.ducnm.tourapp.service.CitiesService;
import com.ducnm.tourapp.service.ConvertToDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesServiceImpl implements CitiesService {
    @Autowired
    private CitiesRepository citiesRepository;
    @Autowired
    private ConvertToDtoService toDtoService;
    @Override
    public CitiesSummaryDto addCities(CitiesSummaryDto citiesDto) {
        Cities cities = Cities.builder()
                .name(citiesDto.getName())
                .build();
        return toDtoService.toCitiesSummaryDto(citiesRepository.save(cities));
    }

    @Override
    public List<CitiesSummaryDto> getAllCities() {
        return toDtoService.toCitiesSummaryListDto(citiesRepository.findAll());
    }

    @Override
    public Cities findByName(String city) {
        return citiesRepository.findByName(city);
    }

    @Override
    public Cities findById(Long id) {
        return citiesRepository.findById(id).orElse(null);
    }

    @Override
    public CitiesDetailsDto getCityById(Long id) {
        Cities cities = citiesRepository.findById(id).orElse(null);
        if (cities == null) return null;
        return toDtoService.toCitiesDetailsDto(cities);
    }

//    private CitiesDto convertEntityToDto(Cities cities) {
//        CitiesDto citiesDto = CitiesDto.builder()
//                .id(cities.getId())
//                .name(cities.getName())
//                .build();
//        return citiesDto;
//    }
}
