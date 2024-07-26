package com.ducnm.tourapp.dto.Customer;

import com.ducnm.tourapp.model.MembershipClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerSummaryDto {
    private Long id;
    private String ho;
    private String ten;
    private String dob;
    private MembershipClass membership;
    private String phone;
    private String email;
}
