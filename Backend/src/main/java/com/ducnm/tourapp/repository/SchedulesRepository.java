package com.ducnm.tourapp.repository;

import com.ducnm.tourapp.model.DayInPackages;
import com.ducnm.tourapp.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulesRepository extends JpaRepository<Schedules, Long> {
    default void softDeleteById(long id) {
        Schedules schedules = findById(id).orElse(null);
        if (schedules != null) {
            schedules.setDeleted(true);
            save(schedules);
        }
    }
}
