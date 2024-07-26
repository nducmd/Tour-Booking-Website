package com.ducnm.tourapp.controller;

import com.ducnm.tourapp.dto.Cities.CitiesSummaryDto;
import com.ducnm.tourapp.model.ResponseObject;
import com.ducnm.tourapp.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/cities")
@CrossOrigin("http://localhost:3000")
public class CitiesController {
    @Autowired
    private CitiesService citiesService;
    @PostMapping("addCities")
    ResponseEntity<ResponseObject> addProduct(@RequestBody CitiesSummaryDto citiesDto) {
        CitiesSummaryDto newCitiesDto = citiesService.addCities(citiesDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Them city thanh cong", newCitiesDto)
        );
    }
}
