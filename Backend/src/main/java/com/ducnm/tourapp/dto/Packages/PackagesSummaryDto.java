package com.ducnm.tourapp.dto.Packages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PackagesSummaryDto {
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
}
