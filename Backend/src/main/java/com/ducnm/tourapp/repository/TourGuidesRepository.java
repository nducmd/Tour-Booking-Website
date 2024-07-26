package com.ducnm.tourapp.repository;


import com.ducnm.tourapp.model.Customers;
import com.ducnm.tourapp.model.TourGuides;
import com.ducnm.tourapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TourGuidesRepository extends JpaRepository<TourGuides, Long> {
    TourGuides findByUser(User user);
    @Query("SELECT s FROM TourGuides s WHERE lower(concat(s.ho, ' ', s.ten)) LIKE lower(concat('%', :keyword, '%')) OR lower(s.phone) LIKE lower(concat('%', :keyword, '%'))")
    Page<TourGuides> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
