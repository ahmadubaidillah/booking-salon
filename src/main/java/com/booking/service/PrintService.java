package com.booking.service;

import java.util.List;

import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;

public class PrintService {
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public static String printServices(List<Service> serviceList){
        StringBuilder result = new StringBuilder();
        for (Service service : serviceList) {
            result.append(service.getServiceName()).append(", ");
        }
        if (result.length() > 0) {
            result.setLength(result.length() - 2); // Remove the last comma and space
        }
        return result.toString();
    }

    public static void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("In Process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                        num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }

    public static void showAllCustomer(List<Person> personList) {
        int num = 1;
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-10s | %-10s |\n",
                "No.", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out.println("+==============================================================+");
        for (Person person : personList) {
            if (person instanceof Customer) {
                Customer customer = (Customer) person;
                System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-10s | Rp%-9.2f |\n",
                        num, customer.getId(), customer.getName(), customer.getAddress(), customer.getMember().getMembershipName(), customer.getWallet());
                num++;
            }
        }
    }

    public static void showAllEmployee(List<Person> personList) {
        int num = 1;
        System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-10s |\n",
                "No.", "ID", "Nama", "Alamat", "Experience");
        System.out.println("+========================================================+");
        for (Person person : personList) {
            if (person instanceof Employee) {
                Employee employee = (Employee) person;
                System.out.printf("| %-4s | %-7s | %-11s | %-15s | %-10d |\n",
                        num, employee.getId(), employee.getName(), employee.getAddress(), employee.getExperience());
                num++;
            }
        }
    }

    public static void showHistoryReservation(List<Reservation> reservationList) {
        double totalProfit = 0;
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+========================================================================================+");
        for (Reservation reservation : reservationList) {
            if (!reservation.getWorkstage().equalsIgnoreCase("In Process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                        num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                totalProfit += reservation.getReservationPrice();
                num++;
            }
        }
        System.out.printf("Total Keuntungan: Rp%.2f\n", totalProfit);
    }
}
