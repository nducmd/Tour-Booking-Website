package com.ducnm.tourapp.dto.Vouchers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VouchersBasicDto {
    private Long id;
    private String code;
    private Long percent;
}
