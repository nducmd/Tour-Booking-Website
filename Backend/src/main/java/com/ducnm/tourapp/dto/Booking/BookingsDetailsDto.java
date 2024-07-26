package com.ducnm.tourapp.dto.Booking;

import com.ducnm.tourapp.dto.Customer.CustomerBasicDto;
import com.ducnm.tourapp.dto.Customer.CustomerDetailsDto;
import com.ducnm.tourapp.dto.Packages.PackagesDetailsDto;
import com.ducnm.tourapp.dto.Packages.PackagesSummaryDto;
import com.ducnm.tourapp.dto.Vouchers.VouchersBasicDto;
import com.ducnm.tourapp.dto.Vouchers.VouchersDetailsDto;
import com.ducnm.tourapp.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingsDetailsDto {
    private Long id;
    private String code;
    private Long numberOfPeople;
    private Long subtotal;
    private Long voucher;
    private Long member;
    private Long total;
    private String note;
    private boolean pickup;
    private String createAt;
    private String updateAt;
    private PaymentStatus paymentStatus;
    private CustomerBasicDto customers;
    private PackagesSummaryDto packages;
    private VouchersBasicDto vouchers;
}
