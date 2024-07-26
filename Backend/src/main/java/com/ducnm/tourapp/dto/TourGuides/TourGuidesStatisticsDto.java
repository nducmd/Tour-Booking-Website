package com.ducnm.tourapp.dto.TourGuides;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TourGuidesStatisticsDto {
    private long tourguideCount;
}
