package com.ducnm.tourapp.dto.Booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingStatisticsDto {
    private int bookingCount;
    private long totalRevenue;
}
