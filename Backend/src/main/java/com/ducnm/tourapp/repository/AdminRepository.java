package com.ducnm.tourapp.repository;

import com.ducnm.tourapp.model.Admin;
import com.ducnm.tourapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    Admin findByUser(User user);
}