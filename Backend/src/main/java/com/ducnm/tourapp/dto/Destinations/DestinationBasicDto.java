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
public class DestinationBasicDto {
    private Long id;
    private String name;
    private String address;
    private Double rate;
    private Long price;
    private String slug;
    private String image;
    private CitiesSummaryDto city;
}
