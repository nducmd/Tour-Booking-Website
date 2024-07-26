package com.ducnm.tourapp.dto.Destinations;

import com.ducnm.tourapp.dto.Cities.CitiesSummaryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DestinationSimpleDto {
    private Long id;
    private String name;
}