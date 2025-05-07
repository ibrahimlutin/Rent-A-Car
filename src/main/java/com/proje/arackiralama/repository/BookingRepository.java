package com.proje.arackiralama.repository;

import com.proje.arackiralama.model.Booking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // Kullanıcı adına göre tüm rezervasyonları getirir
    List<Booking> findByUsername(String username);

    // Belirli bir araç ID'sine göre rezervasyon getirir (tekil olabilir)
    Optional<Booking> findByCarId(Integer carId);

    // Belirli bir sürücü ID'sine göre rezervasyon getirir (tekil olabilir)
    Optional<Booking> findByDriverId(Integer driverId);
}
