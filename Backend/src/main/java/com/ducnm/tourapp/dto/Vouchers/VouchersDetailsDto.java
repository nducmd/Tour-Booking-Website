package com.ducnm.tourapp.dto.Vouchers;

import com.ducnm.tourapp.dto.Booking.BookingsBasicDto;
import com.ducnm.tourapp.model.Bookings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VouchersDetailsDto {
    private Long id;
    private String name;
    private String code;
    private String startTime;
    private String endTime;
    private Long amount;
    private Long percent;
    private List<BookingsBasicDto> bookingsList;
}
