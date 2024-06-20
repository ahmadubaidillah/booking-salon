package com.booking.service;

public class ValidationService {
    // Function untuk memvalidasi input, sesuai kebutuhan
    public static boolean validateInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Function untuk memvalidasi ID customer, sesuai kebutuhan
    public static boolean validateCustomerId(String customerId) {
        return customerId != null && !customerId.trim().isEmpty();
    }
}
