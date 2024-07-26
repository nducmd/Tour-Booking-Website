package com.ducnm.tourapp.auth;

import com.ducnm.tourapp.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String ho;
    private String ten;
    private String title;
    private Long salary;
    private String email;
    private String password;
    private Role role;
}
