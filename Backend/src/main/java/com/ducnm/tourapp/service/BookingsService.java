package com.ducnm.tourapp.service;

import com.ducnm.tourapp.dto.Booking.BookingStatisticsDto;
import com.ducnm.tourapp.dto.Booking.BookingsBasicDto;
import com.ducnm.tourapp.dto.Booking.BookingsDetailsDto;
import com.ducnm.tourapp.dto.Booking.BookingsSummaryDto;
import com.ducnm.tourapp.model.PaymentStatus;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingsService {
    String addBooking(BookingsDetailsDto bookingsDetailsDto);
    BookingsDetailsDto getBookingById(Long id);
    void cancelBooking(Long id);
    boolean confirmBooking(Long id) throws MessagingException;
    PaymentStatus checkBooking(String code) throws MessagingException;
    List<BookingsSummaryDto> getAllBookings();
    Page<BookingsSummaryDto> getAllBookingsPage(int page);
    Map<LocalDate, BookingStatisticsDto> getBookingStatisticsByDayOfMonth(int year, int month);
    Page<BookingsSummaryDto> searchBookings(String keyword, int page);
    Page<BookingsBasicDto> searchCustomerBookings(Long customerId, String keyword, int page);
    Page<BookingsBasicDto> searchVoucherBookings(Long voucherId, String keyword, int page);
    Page<BookingsBasicDto> searchCustomerBookings(String authorizationHeader, String keyword, int page);
}
