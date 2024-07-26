package com.ducnm.tourapp.dto.Packages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PackagesStatisticsDto {
    private long packagesCount;
    private long available;
    private long upcoming;
    private long finished;
}
