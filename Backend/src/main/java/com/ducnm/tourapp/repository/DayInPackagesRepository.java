package com.ducnm.tourapp.repository;

import com.ducnm.tourapp.model.DayInPackages;
import com.ducnm.tourapp.model.Packages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayInPackagesRepository extends JpaRepository<DayInPackages, Long> {
    default void softDeleteById(long id) {
        DayInPackages dayInPackages = findById(id).orElse(null);
        if (dayInPackages != null) {
            dayInPackages.setDeleted(true);
            save(dayInPackages);
        }
    }
}
