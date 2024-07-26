package com.ducnm.tourapp.dto.Packages;

import com.ducnm.tourapp.dto.DayInPackages.DayInPackagesDetailsDto;
import com.ducnm.tourapp.dto.TourGuides.TourGuidesBasicDto;
import com.ducnm.tourapp.dto.TourGuides.TourGuidesDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PackagesDetailsDto {
    private Long id;
    private String name;
    private Long capacity;
    private Long available;
    private Long cost;
    private String startDate;
    private String endDate;
    private String description;
    private String slug;
    private String image1;
    private List<String> imageList;
    private List<String> imageHotelList;
    private TourGuidesBasicDto tourGuidesDto;
    //private List<Bookings> bookingsList;
    private List<DayInPackagesDetailsDto> dayList;
}
