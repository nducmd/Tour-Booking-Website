package com.ducnm.tourapp.dto.Admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AdminDetailsDto {
    private Long id;
    private String ho;
    private String ten;
    private String title;
    private String email;
}
