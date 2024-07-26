package com.ducnm.tourapp.dto.TourGuides;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TourGuidesBasicDto {
    private Long id;
    private String ho;
    private String ten;
    private String dob;
    private String phone;
}
