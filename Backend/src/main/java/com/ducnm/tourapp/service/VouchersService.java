package com.ducnm.tourapp.service;


import com.ducnm.tourapp.dto.Vouchers.VouchersDetailsDto;
import com.ducnm.tourapp.dto.Vouchers.VouchersStatisticsDto;
import com.ducnm.tourapp.dto.Vouchers.VouchersSummaryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VouchersService {

    VouchersSummaryDto addVouchers(VouchersSummaryDto vouchersSummaryDto);
    VouchersSummaryDto editVouchers(Long id, VouchersSummaryDto vouchersSummaryDto);
    List<VouchersSummaryDto> getAllVouchers();
    VouchersSummaryDto getVouchersById(Long id);
    void deleteVouchers(Long id);
    Long getPercent(VouchersDetailsDto vouchersDetailsDto);

    Page<VouchersSummaryDto> searchVouchers(String keyword, int page);

    VouchersStatisticsDto getVoucherStats();
}
