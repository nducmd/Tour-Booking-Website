package com.ducnm.tourapp.dto.Stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatsDto {
    private Long customersNumber;
    private Long tourguidesNumber;
    private Long packagesNumber;
    private Long bookingsNumber;
    private Long expiredBookingsNumber;
    private Long pendingBookingsNumber;
    private Long paidBookingsNumber;
    private Long finishedPackages;
    private Long ongoingPackages;
    private Long upcomingPackages;
}
