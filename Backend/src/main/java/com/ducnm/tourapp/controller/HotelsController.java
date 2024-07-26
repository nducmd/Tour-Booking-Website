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
@RequestMapping(path = "/api/v1/admin/hotels")
@CrossOrigin("http://localhost:3000")
public class HotelsController {
    @Autowired
    private DestinationService destinationService;

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getHotels(@PathVariable Long id) {
        try{
            DestinationsDetailsDto destinationsDetailsDto = destinationService.getHotelById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy thông tin khách sạn thành công", destinationsDetailsDto)
            );
        } catch(NotFoundException ex){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }

    }
    @PostMapping("")
    ResponseEntity<ResponseObject> addHotels(@RequestBody DestinationsDetailsDto destinationsDetailsDto) {
        try {
            DestinationsDetailsDto hotelsDto = destinationService.addHotels(destinationsDetailsDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Thêm khách sạn thành công", hotelsDto)
            );
        } catch (InputException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        }
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> editHotels(@RequestBody DestinationsDetailsDto destinationsDetailsDto,
                                              @PathVariable Long id) {
        try {
            DestinationsDetailsDto updatedHotelsDto = destinationService.editHotels(id, destinationsDetailsDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Cập nhật khách sạn thành công", updatedHotelsDto)
            );
        } catch (InputException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        }

    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteHotels(@PathVariable Long id) {
        destinationService.deleteHotels(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xoá khách sạn thành công", null)
        );
    }
}
