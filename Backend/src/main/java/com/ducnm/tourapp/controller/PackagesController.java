package com.ducnm.tourapp.controller;

import com.ducnm.tourapp.dto.Booking.BookingsBasicDto;
import com.ducnm.tourapp.dto.DayInPackages.DayInPackagesDetailsDto;
import com.ducnm.tourapp.dto.DayInPackages.DayInPackagesSummaryDto;
import com.ducnm.tourapp.dto.Packages.PackagesDetailsDto;
import com.ducnm.tourapp.dto.Packages.PackagesSummaryDto;
import com.ducnm.tourapp.dto.Schedules.SchedulesDetailsDto;
import com.ducnm.tourapp.exception.InputException;
import com.ducnm.tourapp.exception.NoContentException;
import com.ducnm.tourapp.exception.NotFoundException;
import com.ducnm.tourapp.exception.ScheduleException;
import com.ducnm.tourapp.model.ResponseObject;
import com.ducnm.tourapp.service.DayInPackagesService;
import com.ducnm.tourapp.service.PackagesService;
import com.ducnm.tourapp.service.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/admin/packages")
@CrossOrigin("http://localhost:3000")
public class PackagesController {
    @Autowired
    private PackagesService packagesService;
    @Autowired
    private DayInPackagesService dayInPackagesService;
    @Autowired
    private SchedulesService schedulesService;
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getPackageById(@PathVariable Long id) {
        try{
            PackagesDetailsDto packagesDetailsDto = packagesService.getPackagesById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy thông tin gói tour", packagesDetailsDto)
            );
        }catch(NotFoundException ex){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
    }

    @GetMapping("/{id}/bookingList")
    ResponseEntity<ResponseObject> getBookingListPackageById(@PathVariable Long id) {
        try{
            List<BookingsBasicDto> basicDtoList = packagesService.getBookingList(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách booking gói tour thành công", basicDtoList)
            );
        } catch(NotFoundException ex){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        } catch(NoContentException ex){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", ex.getMessage(), null)
            );
        }
    }


    @PostMapping("")
    ResponseEntity<ResponseObject> addPackages(@RequestBody PackagesDetailsDto newPackageDto) {
        try {
            PackagesSummaryDto packagesSummaryDto = packagesService.addPackages(newPackageDto);
            if (packagesSummaryDto == null) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("error", "Thêm gói tour không thành công", null)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Thêm gói tour thành công", packagesSummaryDto)
            );
        } catch (ScheduleException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
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

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> editPackages(@RequestBody PackagesDetailsDto packagesDetailsDto, @PathVariable Long id) {
        try {
            PackagesSummaryDto packagesSummaryDto = packagesService.editPackages(id, packagesDetailsDto);
            if (packagesSummaryDto == null) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("error", "Sửa gói tour không thành công", null)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Sửa gói tour thành công", packagesSummaryDto)
            );
        } catch (ScheduleException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
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
    ResponseEntity<ResponseObject> deletePackages(@PathVariable Long id) {
        packagesService.deletePackages(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xoá gói tour thành công", null)
        );
    }

    @PostMapping("/{packageId}")
    ResponseEntity<ResponseObject> addDays(@RequestBody DayInPackagesDetailsDto newDaysDto,
                                           @PathVariable Long packageId) {
        try {
            DayInPackagesSummaryDto dayInPackagesDetailsDto = dayInPackagesService.addDayInPackages(packageId, newDaysDto);
            if (dayInPackagesDetailsDto == null) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("error", "Thêm ngày không thành công", null)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Thêm ngày thành công", dayInPackagesDetailsDto)
            );
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        } catch (ScheduleException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        }

    }

    @PutMapping("/{packagedId}/{dayId}")
    ResponseEntity<ResponseObject> editDays(@RequestBody DayInPackagesDetailsDto newDaysDto,
                                            @PathVariable Long packagedId,
                                            @PathVariable Long dayId) {
        try{
            DayInPackagesSummaryDto dayInPackagesDetailsDto = dayInPackagesService.editDayInPackages(packagedId, dayId, newDaysDto);
            if (dayInPackagesDetailsDto == null) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("error", "Sửa ngày không thành công", null)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Sửa ngày thành công", dayInPackagesDetailsDto)
            );
        } catch (ScheduleException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        }
    }
    @DeleteMapping("/{packagedId}/{dayId}")
    ResponseEntity<ResponseObject> deleteDays(@PathVariable Long packagedId,
                                                  @PathVariable Long dayId) {
        dayInPackagesService.deleteDay(dayId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xoá ngày thành công", null)
        );
    }

    @PostMapping("/{packagedId}/{dayId}")
    ResponseEntity<ResponseObject> addSchedules(@RequestBody SchedulesDetailsDto newSchedulesDetailsDto,
                                                @PathVariable Long packagedId,
                                                @PathVariable Long dayId) {
        try{
            SchedulesDetailsDto schedulesDetailsDto = schedulesService.addScheduels(dayId, newSchedulesDetailsDto);
            if (schedulesDetailsDto == null) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("error", "Thêm lich trình không thành công", null)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Thêm lịch trình thành công", schedulesDetailsDto)
            );
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        } catch (InputException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        }
    }

    @PutMapping("/{packagedId}/{dayId}/{scheduleId}")
    ResponseEntity<ResponseObject> editSchedules(@RequestBody SchedulesDetailsDto newSchedulesDetailsDto,
                                                 @PathVariable Long packagedId,
                                                 @PathVariable Long dayId,
                                                 @PathVariable Long scheduleId) {
        SchedulesDetailsDto schedulesDetailsDto = schedulesService.editSchedules(dayId, newSchedulesDetailsDto);
        if (schedulesDetailsDto == null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Sửa lịch trình không thành công", null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Sửa lịch trình thành công", schedulesDetailsDto)
        );
    }
    @DeleteMapping("/{packagedId}/{dayId}/{scheduleId}")
    ResponseEntity<ResponseObject> deleteSchedules(@PathVariable Long packagedId,
                                                 @PathVariable Long dayId,
                                                 @PathVariable Long scheduleId) {
        schedulesService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xoá lịch trình thành công", null)
        );
    }

}