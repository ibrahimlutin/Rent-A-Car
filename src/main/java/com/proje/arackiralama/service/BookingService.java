package com.proje.arackiralama.service;

import com.proje.arackiralama.dao.BookingDao;
import com.proje.arackiralama.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingDao bookingDao;

    public List<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

    public Optional<Booking> getBooking(int id) {
        return bookingDao.getBookingById(id);
    }

    public void deleteBooking(int id) {
        bookingDao.deleteById(id);
    }

    public void saveBooking(Booking booking) {
        bookingDao.saveBooking(booking);
    }

    public Optional<Booking> getByCarId(int carId) {
        return bookingDao.getByCarId(carId);
    }

    public Optional<Booking> getByDriverId(int driverId) {
        return bookingDao.getByDriverId(driverId);
    }

    public List<Booking> getByUsername(String username) {
        return bookingDao.getByUsername(username);
    }
}
