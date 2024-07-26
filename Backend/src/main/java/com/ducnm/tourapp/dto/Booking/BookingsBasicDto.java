package com.ducnm.tourapp.dto.Booking;

import com.ducnm.tourapp.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingsBasicDto {
    private Long id;
    private String code;
    private Long total;
    private String note;
    private String createAt;
    private PaymentStatus paymentStatus;
}
