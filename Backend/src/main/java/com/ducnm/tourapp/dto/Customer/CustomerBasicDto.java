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
public class CustomerBasicDto {
    private Long id;
    private String ho;
    private String ten;
    private MembershipClass membership;
}

