package com.ducnm.tourapp.controller;

import com.ducnm.tourapp.auth.Token;
import com.ducnm.tourapp.dto.Customer.CustomerDetailsDto;
import com.ducnm.tourapp.dto.Customer.CustomerSummaryDto;
import com.ducnm.tourapp.dto.Vouchers.VouchersDetailsDto;
import com.ducnm.tourapp.exception.BookingException;
import com.ducnm.tourapp.exception.NoContentException;
import com.ducnm.tourapp.exception.NotFoundException;
import com.ducnm.tourapp.model.PaymentStatus;
import com.ducnm.tourapp.model.ResponseObject;
import com.ducnm.tourapp.service.BookingsService;
import com.ducnm.tourapp.service.CustomerService;
import com.ducnm.tourapp.service.VNPayService;
import com.ducnm.tourapp.service.VouchersService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/customers")
@CrossOrigin("http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private VouchersService vouchersService;
    @Autowired
    private BookingsService bookingsService;
    @Autowired
    private VNPayService vnPayService;
    @GetMapping("/getInfo")
    ResponseEntity<ResponseObject> getCustomer(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7);
            CustomerSummaryDto customerSummaryDto = customerService.getCustomerDtoByToken(new Token(accessToken, ""));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy thông tin khách hàng thành công", customerSummaryDto)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Lấy thông tin khách hàng không thành công", null)
            );
        }
    }
    @GetMapping("/searchBookings")
    ResponseEntity<ResponseObject> searchBookings(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        try{
            Page<?> bookingsPage;
                bookingsPage = bookingsService.searchCustomerBookings(authorizationHeader, keyword, page);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách đơn hàng thành công", bookingsPage)
            );
        } catch (NoContentException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", ex.getMessage(), null)
            );
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", ex.getMessage(), null)
            );
        }
    }


    @PutMapping("/editInfo")
    ResponseEntity<ResponseObject> editCustomerInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                                    @RequestBody CustomerDetailsDto newCustomerDetailsDto) {

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String accessToken = authorizationHeader.substring(7);
            CustomerSummaryDto customerSummaryDto = customerService.editCustomerInfo(new Token(accessToken, ""), newCustomerDetailsDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Sửa thông tin khách hàng thành công", customerSummaryDto)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Sửa thông tin khách hàng không thành công", null)
            );
        }
    }
    @PostMapping("/checkVoucher")
    ResponseEntity<ResponseObject> checkVoucher(@RequestBody VouchersDetailsDto vouchersDetailsDto) {
        if (vouchersService.getPercent(vouchersDetailsDto) > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Mã giảm giá khả dụng", vouchersService.getPercent(vouchersDetailsDto))
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("error", "Mã giảm giá không khả dụng", 0)
        );
    }
    @PostMapping("/checkBooking")
    ResponseEntity<ResponseObject> checkBooking(@RequestBody Map<String, String> paramMap) {
        try {
            if (!vnPayService.checkReturnUrl(paramMap)) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("error", "Lỗi chữ ký số", null)
                );
            }
            PaymentStatus ps = bookingsService.checkBooking(paramMap.get("vnp_OrderInfo"));
            if (ps.equals(PaymentStatus.EXPIRED)) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Đơn hàng đã hết hạn thanh toán", PaymentStatus.EXPIRED)
                );
            } else if (ps.equals(PaymentStatus.PAID)) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Đơn hàng đã thanh toán", PaymentStatus.PAID)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Đơn hàng chưa thanh toán", PaymentStatus.PENDING)
            );
        } catch (BookingException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", e.getMessage(), null)
            );
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
