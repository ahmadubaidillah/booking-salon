package com.booking.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private String reservationId; // ID unik untuk reservasi
    private Customer customer; // Customer yang melakukan reservasi
    private Employee employee; // Employee yang melayani reservasi
    private List<Service> services; // Daftar layanan yang dipesan
    private double reservationPrice; // Total harga reservasi
    private String workstage; // Tahap kerja reservasi (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = calculateReservationPrice(); // Menghitung total harga reservasi
        this.workstage = workstage;
    }

    // Method untuk menghitung total harga reservasi
    private double calculateReservationPrice() {
        double total = services.stream().mapToDouble(Service::getPrice).sum();
        double discount = 0;
        switch (customer.getMember().getMembershipName().toLowerCase()) {
            case "silver":
                discount = 0.05; // Diskon 5% untuk member Silver
                break;
            case "gold":
                discount = 0.10; // Diskon 10% untuk member Gold
                break;
        }
        return total * (1 - discount);
    }
}
