package com.ducnm.tourapp.dto.TourGuides;

import com.ducnm.tourapp.dto.Packages.PackagesDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TourGuidesDetailsDto {
    private Long id;
    private String ho;
    private String ten;
    private String dob;
    private String phone;
    private Long salary;
    private String email;
    private List<PackagesDetailsDto> packagesList;
}
