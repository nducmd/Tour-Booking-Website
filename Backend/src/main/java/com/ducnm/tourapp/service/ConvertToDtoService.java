package com.ducnm.tourapp.service;

import com.ducnm.tourapp.dto.Admin.AdminDetailsDto;
import com.ducnm.tourapp.dto.Booking.BookingsBasicDto;
import com.ducnm.tourapp.dto.Booking.BookingsDetailsDto;
import com.ducnm.tourapp.dto.Booking.BookingsSummaryDto;
import com.ducnm.tourapp.dto.Cities.CitiesDetailsDto;
import com.ducnm.tourapp.dto.Cities.CitiesSummaryDto;
import com.ducnm.tourapp.dto.Customer.CustomerBasicDto;
import com.ducnm.tourapp.dto.Customer.CustomerDetailsDto;
import com.ducnm.tourapp.dto.Customer.CustomerSummaryDto;
import com.ducnm.tourapp.dto.DayInPackages.DayInPackagesDetailsDto;
import com.ducnm.tourapp.dto.DayInPackages.DayInPackagesSummaryDto;
import com.ducnm.tourapp.dto.Destinations.DestinationBasicDto;
import com.ducnm.tourapp.dto.Destinations.DestinationSimpleDto;
import com.ducnm.tourapp.dto.Destinations.DestinationsDetailsDto;
import com.ducnm.tourapp.dto.Destinations.DestinationsSummaryDto;
import com.ducnm.tourapp.dto.Packages.PackagesBasicDto;
import com.ducnm.tourapp.dto.Packages.PackagesDetailsDto;
import com.ducnm.tourapp.dto.Packages.PackagesSummaryDto;
import com.ducnm.tourapp.dto.Schedules.SchedulesDetailsDto;
import com.ducnm.tourapp.dto.TourGuides.TourGuidesBasicDto;
import com.ducnm.tourapp.dto.TourGuides.TourGuidesSummaryDto;
import com.ducnm.tourapp.dto.Vouchers.VouchersBasicDto;
import com.ducnm.tourapp.dto.Vouchers.VouchersDetailsDto;
import com.ducnm.tourapp.dto.Vouchers.VouchersSummaryDto;
import com.ducnm.tourapp.model.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConvertToDtoService {
    AdminDetailsDto toAdminDetailsDto(Admin admin);

    BookingsDetailsDto toBookingsDetailsDto(Bookings bookings);
    BookingsBasicDto toBookingsBasicDto(Bookings bookings);
    BookingsSummaryDto toBookingsSummaryDto(Bookings bookings);
    List<BookingsSummaryDto> toBookingsSummaryDtoList(List<Bookings> bookingsList);
    List<BookingsBasicDto> toBookingsBasicDtoList(List<Bookings> bookingsList);
    List<BookingsDetailsDto> toBookingsDetailsDtoList(List<Bookings> bookingsList);
    Page<BookingsSummaryDto> toBookingsSummaryDtoList(Page<Bookings> bookingsPage);
    Page<BookingsBasicDto> toBookingsBasicDtoList(Page<Bookings> bookingsPage);

    CitiesDetailsDto toCitiesDetailsDto(Cities cities);
    CitiesSummaryDto toCitiesSummaryDto(Cities cities);
    List<CitiesSummaryDto> toCitiesSummaryListDto(List<Cities> cities);

    CustomerSummaryDto toCustomerSummaryDto(Customers customers);
    CustomerDetailsDto toCustomerDetailsDto(Customers customers);
    CustomerBasicDto toCustomerBasicDto(Customers customers);
    List<CustomerDetailsDto> toCustomerDetailsDtoList(List<Customers> customersList);
    List<CustomerSummaryDto> toCustomerSummaryDtoList(List<Customers> customersList);
    Page<CustomerSummaryDto> toCustomerSummaryDtoPage(Page<Customers> customersPage);


    DayInPackagesDetailsDto toDayInPackagesDetailsDto(DayInPackages dayInPackages);
    DayInPackagesSummaryDto toDayInPackagesSummaryDto(DayInPackages dayInPackages);
    List<DayInPackagesDetailsDto> toDayInPackagesDetailsDtoList(List<DayInPackages> dayInPackages);

    DestinationsSummaryDto toDestinationsSummaryDto(Destinations destinations);
    DestinationBasicDto toDestinationBasicDto(Destinations destinations);
    DestinationSimpleDto toDestinationSimpleDto(Destinations destinations);
    List<DestinationsSummaryDto> toDestinationsSummaryDtoList(List<Destinations> destinationsList);
    List<DestinationSimpleDto> toDestinationsSimpleDto(List<Destinations> allAndDeletedFalse);
    DestinationsDetailsDto toDestinationsDetailsDto(Destinations destinations);
    List<DestinationsDetailsDto> toDestinationsDetailsDtoList(List<Destinations> destinationsList);
    Page<DestinationsDetailsDto> toDestinationsDetailsDtoPage(Page<Destinations> destinationsPage);
    Page<DestinationsSummaryDto> toDestinationsSummaryDtoPage(Page<Destinations> destinationsPage);
    Page<DestinationBasicDto> toDestinationBasicDtoPage(Page<Destinations> destinationsPage);

    PackagesDetailsDto toPackagesDetailsDto(Packages packages);
    PackagesBasicDto toPackagesBasicDto(Packages packages);
    PackagesSummaryDto toPackagesSummaryDto(Packages packages);
    List<PackagesSummaryDto> toPackagesSummaryDtoList(List<Packages> packagesList);
    List<PackagesBasicDto> toPackagesBasicDtoList(List<Packages> packagesList);
    Page<PackagesBasicDto> toPackagesBasicDtoPage(Page<Packages> packagesPage);


    PackagesDetailsDto convertPackagesForTG(Packages packages);
    List<PackagesDetailsDto> convertPackagesListForTG (List<Packages> packagesList);

    List<BookingsDetailsDto> convertBookingsListForCustomer (List<Bookings> bookingList);

    SchedulesDetailsDto toSchedulesDetailsDto(Schedules schedules);
    List<SchedulesDetailsDto> toSchedulesDetailsDtoList(List<Schedules> schedulesList);

    TourGuidesBasicDto toTourGuidesBasic(TourGuides tourGuides);
    TourGuidesSummaryDto toTourGuidesSummaryDto(TourGuides tourGuides);
    List<TourGuidesSummaryDto> toTourGuidesSummaryDtoList(List<TourGuides> tourGuidesList);
    Page<TourGuidesSummaryDto> toTourGuidesSummaryDtoPage(Page<TourGuides> tourGuidesPage);

    VouchersDetailsDto toVouchersDetailsDto(Vouchers vouchers);
    List<VouchersDetailsDto> toVouchersDetailsDtoList(List<Vouchers> vouchersList);
    VouchersBasicDto toVouchersBasicDto(Vouchers vouchers);
    VouchersSummaryDto toVouchersSummaryDto(Vouchers vouchers);
    List<VouchersSummaryDto> toVouchersSummaryDtoList(List<Vouchers> vouchersList);
    Page<VouchersSummaryDto> toVouchersSummaryDtoPage(Page<Vouchers> vouchersPage);


}
