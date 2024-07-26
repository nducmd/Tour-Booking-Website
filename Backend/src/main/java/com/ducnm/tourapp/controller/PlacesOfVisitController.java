package com.ducnm.tourapp.controller;

import com.ducnm.tourapp.dto.Destinations.DestinationsDetailsDto;
import com.ducnm.tourapp.exception.InputException;
import com.ducnm.tourapp.exception.NotFoundException;
import com.ducnm.tourapp.model.ResponseObject;
import com.ducnm.tourapp.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/admin/places")
@CrossOrigin("http://localhost:3000")
public class PlacesOfVisitController {
    @Autowired
    private DestinationService destinationService;

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getPlaces(@PathVariable Long id) {
        try {
            DestinationsDetailsDto destinationsDetailsDto = destinationService.getPlacesOfVisitById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy địa điểm thành công", destinationsDetailsDto)
            );
        } catch(NotFoundException ex){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addPlacesOfVisit(@RequestBody DestinationsDetailsDto destinationsDetailsDto) {
        try {
            DestinationsDetailsDto placesDto = destinationService.addPlacesOfVisit(destinationsDetailsDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Thêm địa điểm thành công", placesDto)
            );
        } catch (InputException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        }

    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> editPlacesOfVisit(@RequestBody DestinationsDetailsDto destinationsDetailsDto, @PathVariable Long id) {
        try {
            DestinationsDetailsDto updatedPlacesDto = destinationService.editPlacesOfVisit(id, destinationsDetailsDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Cập nhật địa điểm thành công", updatedPlacesDto)
            );
        } catch (InputException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        } catch(NotFoundException ex){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deletePlaces(@PathVariable Long id) {
        destinationService.deletePlaces(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xoá địa điểm thành công", null)
        );
    }
}
