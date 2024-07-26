package com.ducnm.tourapp.dto.Vouchers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VouchersSummaryDto {
    private Long id;
    private String name;
    private String code;
    private String startTime;
    private String endTime;
    private Long amount;
    private Long percent;
}
