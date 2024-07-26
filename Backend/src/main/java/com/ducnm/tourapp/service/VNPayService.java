package com.ducnm.tourapp.service;

import com.ducnm.tourapp.model.Bookings;
import com.ducnm.tourapp.model.VNPay;

import java.util.Map;

public interface VNPayService {
    String createPayment(Bookings bookings);
    int queryPayment(VNPay vnPay);
    boolean checkReturnUrl(Map<String, String> params);
}
