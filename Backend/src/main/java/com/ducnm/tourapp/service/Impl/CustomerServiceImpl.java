package com.ducnm.tourapp.service.Impl;

import com.ducnm.tourapp.auth.Token;
import com.ducnm.tourapp.config.JwtService;
import com.ducnm.tourapp.dto.Customer.CustomerDetailsDto;
import com.ducnm.tourapp.dto.Customer.CustomerStatisticsDto;
import com.ducnm.tourapp.dto.Customer.CustomerSummaryDto;
import com.ducnm.tourapp.exception.NoContentException;
import com.ducnm.tourapp.exception.NotFoundException;
import com.ducnm.tourapp.model.Customers;
import com.ducnm.tourapp.model.MembershipClass;
import com.ducnm.tourapp.model.User;
import com.ducnm.tourapp.repository.CustomersRepository;
import com.ducnm.tourapp.service.ConvertToDtoService;
import com.ducnm.tourapp.service.CustomerService;
import com.ducnm.tourapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private ConvertToDtoService toDtoService;
    @Override
    public Customers addCustomers(CustomerDetailsDto customerDetailsDto, User user) {
        Customers customers = Customers.builder()
                .ho(customerDetailsDto.getHo())
                .ten(customerDetailsDto.getTen())
                .membership(customerDetailsDto.getMembership())
                .user(user)
                .build();
        return customersRepository.save(customers);
    }

    @Override
    public CustomerSummaryDto getCustomerDtoByToken(Token token) {
        String email = jwtService.extractUsername(token.getToken());
        User user = userService.findUserByEmail(email);
        Customers customers = customersRepository.findByUser(user);
        return toDtoService.toCustomerSummaryDto(customers);
    }

    @Override
    public CustomerSummaryDto editCustomerInfo(Token token, CustomerDetailsDto newCustomerDetailsDto) {
        String email = jwtService.extractUsername(token.getToken());
        User user = userService.findUserByEmail(email);
        Customers customers = customersRepository.findByUser(user);
        customers.setHo(newCustomerDetailsDto.getHo());
        customers.setTen(newCustomerDetailsDto.getTen());
        customers.setDob(LocalDate.parse(newCustomerDetailsDto.getDob()));
        customers.setPhone(newCustomerDetailsDto.getPhone());
        return toDtoService.toCustomerSummaryDto(customersRepository.save(customers));
    }

    @Override
    public List<CustomerSummaryDto> getAllCustomers() {
        List<Customers> customersList = customersRepository.findAll();
        if (customersList.isEmpty())
            throw new NoContentException("Không tìm thấy khách hàng nào");
        return toDtoService.toCustomerSummaryDtoList(customersList);
    }

    @Override
    public CustomerSummaryDto getCustomerById(Long id) {
        Customers customers = customersRepository.findById(id).orElse(null);
        if (customers == null)
            throw new NotFoundException("Khách hàng id " + id + " không tồn tại");
        return toDtoService.toCustomerSummaryDto(customers);
    }

    @Override
    public Page<CustomerSummaryDto> searchCustomers(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "id"));
        Page<Customers> customersPage = customersRepository.searchByKeyword(keyword, pageable);
        if (customersPage.isEmpty())  throw new NoContentException("Không tìm thấy khách hàng nào");
        return toDtoService.toCustomerSummaryDtoPage(customersPage);
    }

    @Override
    public CustomerStatisticsDto getCustomerStats() {
        long premium = customersRepository.countByMembership(MembershipClass.PREMIUM);
        long standard = customersRepository.countByMembership(MembershipClass.STANDARD);
        return CustomerStatisticsDto.builder()
                .customerCount(premium + standard)
                .standardCount(standard)
                .premiumCount(premium)
                .build();
    }

    @Override
    public Customers getCustomerByToken(Token token) {
        String email = jwtService.extractUsername(token.getToken());
        User user = userService.findUserByEmail(email);
        if (user == null) throw new NotFoundException("Không tồn tại khách hàng");
        Customers customers = customersRepository.findByUser(user);
        if (customers == null) throw new NotFoundException("Không tồn tại khách hàng");
        return customers;
    }

//    private CustomerDetailsDto convertEntityToDto(Customers customers) {
//        CustomerDetailsDto customerDetailsDto = CustomerDetailsDto.builder()
//                .id(customers.getId())
//                .ho(customers.getHo())
//                .ten(customers.getTen())
//                .membership(customers.getMembership())
//                .phone(customers.getPhone())
//                .email(customers.getUser().getEmail())
//                .bookingsList(toDtoService.convertBookingsListForCustomer(customers.getBookingsList()))
//                .build();
//        if (customers.getDob() != null) {
//            customerDetailsDto.setDob(customers.getDob().toString());
//        }
//        return customerDetailsDto;
//    }
}