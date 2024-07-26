package com.ducnm.tourapp.dto.Destinations;

import com.ducnm.tourapp.dto.Cities.CitiesSummaryDto;
import com.ducnm.tourapp.model.DestinationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DestinationsDetailsDto {
    private Long id;
    private String name;
    private String description;
    private String address;
    private DestinationType destinationType;
    private Double rate;
    private Long price;
    private String slug;
    private List<String> image;
    private Long cityId;
    private String cityName;
    private CitiesSummaryDto city;
}
