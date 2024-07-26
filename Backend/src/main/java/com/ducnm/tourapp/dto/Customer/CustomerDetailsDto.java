package com.ducnm.tourapp.dto.Customer;

import com.ducnm.tourapp.dto.Booking.BookingsDetailsDto;
import com.ducnm.tourapp.model.MembershipClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDetailsDto {
    private Long id;
    private String ho;
    private String ten;
    private String dob;
    private MembershipClass membership;
    private String phone;
    private String email;
    private List<BookingsDetailsDto> bookingsList;
}
