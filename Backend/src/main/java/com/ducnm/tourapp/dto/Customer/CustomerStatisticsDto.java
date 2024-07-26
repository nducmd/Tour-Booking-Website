package com.ducnm.tourapp.dto.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerStatisticsDto {
    private long customerCount;
    private long premiumCount;
    private long standardCount;
}
