package com.ducnm.tourapp.service;

import com.ducnm.tourapp.auth.Token;
import com.ducnm.tourapp.dto.Admin.AdminDetailsDto;
import com.ducnm.tourapp.model.Admin;
import com.ducnm.tourapp.model.User;
import io.jsonwebtoken.ExpiredJwtException;

public interface AdminService {
    Admin addAdmin(AdminDetailsDto adminDetailsDto, User user);
    void deleteAdmin(Long adminId);
    Admin updateAdmin(Long adminId, AdminDetailsDto newAdminDetailsDto);
    AdminDetailsDto findAdminByUser(User user);
    AdminDetailsDto getAdminDtoByToken(Token token);
    AdminDetailsDto editAdminInfo(Token token, AdminDetailsDto newAdminDetailsDto);
}
