package com.ducnm.tourapp.repository;

import com.ducnm.tourapp.model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository extends JpaRepository<Cities, Long> {
    Cities findByName(String city);
}
