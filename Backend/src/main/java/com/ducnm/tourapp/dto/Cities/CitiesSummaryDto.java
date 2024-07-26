package com.ducnm.tourapp.dto.Cities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CitiesSummaryDto {
    private Long id;
    private String name;
}
