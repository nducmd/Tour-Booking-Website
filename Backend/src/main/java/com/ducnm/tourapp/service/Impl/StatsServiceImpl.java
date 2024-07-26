package com.ducnm.tourapp.service.Impl;

import com.ducnm.tourapp.dto.Stats.StatsDto;
import com.ducnm.tourapp.model.PaymentStatus;
import com.ducnm.tourapp.repository.BookingsRepository;
import com.ducnm.tourapp.repository.CustomersRepository;
import com.ducnm.tourapp.repository.PackagesRepository;
import com.ducnm.tourapp.repository.TourGuidesRepository;
import com.ducnm.tourapp.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private PackagesRepository packagesRepository;
    @Autowired
    private BookingsRepository bookingsRepository;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private TourGuidesRepository tourGuidesRepository;


    @Override
    public StatsDto getStats() {
        return StatsDto.builder()
                .customersNumber(customersRepository.count())
                .tourguidesNumber(tourGuidesRepository.count())
                .packagesNumber(packagesRepository.countByDeletedFalse())
                .bookingsNumber(bookingsRepository.count())
                .paidBookingsNumber(bookingsRepository.countByPaymentStatus(PaymentStatus.PAID))
                .expiredBookingsNumber(bookingsRepository.countByPaymentStatus(PaymentStatus.EXPIRED))
                .pendingBookingsNumber(bookingsRepository.countByPaymentStatus(PaymentStatus.PENDING))
                .finishedPackages(packagesRepository.countFinishedPackages())
                .ongoingPackages(packagesRepository.countOngoingPackages())
                .upcomingPackages(packagesRepository.countUpcomingPackages())
                .build();
    }
}
