package com.ducnm.tourapp.dto.DayInPackages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DayInPackagesSummaryDto {
    private Long id;
    private String date;
    private String name;
}
