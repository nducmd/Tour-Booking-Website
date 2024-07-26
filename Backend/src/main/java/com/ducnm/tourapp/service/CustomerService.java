package com.ducnm.tourapp.service;

import com.ducnm.tourapp.auth.Token;
import com.ducnm.tourapp.dto.Customer.CustomerDetailsDto;
import com.ducnm.tourapp.dto.Customer.CustomerStatisticsDto;
import com.ducnm.tourapp.dto.Customer.CustomerSummaryDto;
import com.ducnm.tourapp.model.Customers;
import com.ducnm.tourapp.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    Customers addCustomers(CustomerDetailsDto customerDetailsDto, User user);
    CustomerSummaryDto getCustomerDtoByToken(Token token);
    CustomerSummaryDto editCustomerInfo(Token token, CustomerDetailsDto newCustomerDetailsDto);
    List<CustomerSummaryDto> getAllCustomers();
    CustomerSummaryDto getCustomerById(Long id);
    Page<CustomerSummaryDto> searchCustomers(String keyword, int page);
    CustomerStatisticsDto getCustomerStats();
    Customers getCustomerByToken(Token token);
}
