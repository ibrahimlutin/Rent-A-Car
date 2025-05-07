package com.proje.arackiralama.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer carId;

    private Integer driverId;

    private String username;

    private LocalDate bookingDate;  // ✅ tarih alanı

    public Booking() {}

    public Booking(Integer carId, Integer driverId, String username, LocalDate bookingDate) {
        this.carId = carId;
        this.driverId = driverId;
        this.username = username;
        this.bookingDate = bookingDate;
    }

    // Getter - Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getCarId() { return carId; }
    public void setCarId(Integer carId) { this.carId = carId; }

    public Integer getDriverId() { return driverId; }
    public void setDriverId(Integer driverId) { this.driverId = driverId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
}
