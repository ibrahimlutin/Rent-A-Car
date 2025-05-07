package com.proje.arackiralama.dao; // DAO sınıflarını barındıran paket

// Gerekli sınıflar import ediliyor
import com.proje.arackiralama.model.Booking; // Rezervasyon model sınıfı
import com.proje.arackiralama.repository.BookingRepository; // Spring Data JPA repository arayüzü
import org.springframework.beans.factory.annotation.Autowired; // Bağımlılık enjeksiyonu için
import org.springframework.stereotype.Repository; // Bu sınıfın bir repository/dao bileşeni olduğunu belirtir

import java.util.List;
import java.util.Optional;

@Repository // Spring tarafından repository bean olarak tanımlanır
public class BookingDao {

    @Autowired // Spring, BookingRepository örneğini otomatik olarak enjekte eder
    private BookingRepository bookingRepository;

    // 🔽 Tüm rezervasyonları veritabanından getirir
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll(); // Tüm kayıtları döner
    }

    // 🔽 Belirtilen ID’ye sahip rezervasyonu siler
    public void deleteById(int id) {
        bookingRepository.deleteById(id); // Silme işlemi
    }

    // 🔽 ID ile rezervasyonu bulur
    public Optional<Booking> getBookingById(int id) {
        return bookingRepository.findById(id); // Optional olarak döner (null olabilir)
    }

    // 🔽 Yeni rezervasyon kaydeder veya mevcut olanı günceller
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking); // insert veya update işlemi yapar
    }

    // 🔽 Araca ait rezervasyonu getirir
    public Optional<Booking> getByCarId(int carId) {
        return bookingRepository.findByCarId(carId); // Custom query (BookingRepository içinde tanımlanmalı)
    }

    // 🔽 Sürücüye ait rezervasyonu getirir
    public Optional<Booking> getByDriverId(int driverId) {
        return bookingRepository.findByDriverId(driverId); // Custom query
    }

    // 🔽 Kullanıcı adına göre rezervasyonları listeler
    public List<Booking> getByUsername(String username) {
        return bookingRepository.findByUsername(username); // Custom query (List döner)
    }
}
