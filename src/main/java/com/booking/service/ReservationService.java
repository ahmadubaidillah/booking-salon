package com.booking.service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationService {

    public static void createReservation(List<Person> personList, List<Service> serviceList, List<Reservation> reservationList, Scanner input) {
        // Function to create a reservation
        System.out.println("Creating a new reservation...");
        
        // Select customer
        System.out.println("Select a customer by ID:");
        PrintService.showAllCustomer(personList);
        String customerId = input.nextLine();
        Customer customer = (Customer) personList.stream()
                                    .filter(person -> person instanceof Customer && person.getId().equals(customerId))
                                    .findFirst()
                                    .orElse(null);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        // Select employee
        System.out.println("Select an employee by ID:");
        PrintService.showAllEmployee(personList);
        String employeeId = input.nextLine();
        Employee employee = (Employee) personList.stream()
                                    .filter(person -> person instanceof Employee && person.getId().equals(employeeId))
                                    .findFirst()
                                    .orElse(null);
        if (employee == null) {
            System.out.println("Employee not found!");
            return;
        }

        // Select services
        System.out.println("Select services by ID (comma separated):");
        serviceList.forEach(service -> System.out.printf("%s: %s - Rp%.2f\n", service.getServiceId(), service.getServiceName(), service.getPrice()));
        String[] serviceIds = input.nextLine().split(",");
        List<Service> selectedServices = serviceList.stream()
                                        .filter(service -> java.util.Arrays.asList(serviceIds).contains(service.getServiceId()))
                                        .collect(Collectors.toList());
        if (selectedServices.isEmpty()) {
            System.out.println("No valid services selected!");
            return;
        }

        // Create and add reservation
        Reservation reservation = new Reservation(java.util.UUID.randomUUID().toString(), customer, employee, selectedServices, "In Process");
        reservationList.add(reservation);
        System.out.println("Reservation created successfully!");
    }

    public static void editReservationWorkstage(List<Reservation> reservationList, Scanner input) {
        // Function to edit reservation workstage
        System.out.println("Edit reservation workstage...");
        
        // Select reservation
        System.out.println("Select a reservation by ID:");
        reservationList.forEach(reservation -> System.out.printf("%s: %s - %s\n", reservation.getReservationId(), reservation.getCustomer().getName(), reservation.getWorkstage()));
        String reservationId = input.nextLine();
        Reservation reservation = reservationList.stream()
                                .filter(res -> res.getReservationId().equals(reservationId))
                                .findFirst()
                                .orElse(null);
        if (reservation == null) {
            System.out.println("Reservation not found!");
            return;
        }

        // Change workstage
        System.out.println("Enter new workstage (Finish/Cancel):");
        String newWorkstage = input.nextLine();
        if (!newWorkstage.equalsIgnoreCase("Finish") && !newWorkstage.equalsIgnoreCase("Cancel")) {
            System.out.println("Invalid workstage!");
            return;
        }
        reservation.setWorkstage(newWorkstage);
        System.out.println("Reservation workstage updated successfully!");
    }
}
