package com.proje.arackiralama.repository;

import com.proje.arackiralama.model.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Bu arayüz, Car (araç) nesnesi için veritabanı işlemlerini gerçekleştiren Spring Data JPA repository'sidir
@Transactional // Her işlem transactional (otomatik commit/rollback) olarak yürütülür
public interface CarRepository extends JpaRepository<Car, Integer> {

    // Veritabanından, belirli koltuk sayısına sahip ve şu anda müsait (kiralanabilir) araçları getirir
    List<Car> findBySeatingCapacityAndAvailableForBookingTrue(int seatingCapacity);

    // Model ismi belirtilen prefix (baş harf) ile başlayan araçları (case-insensitive) arar
    List<Car> findByModelStartingWithIgnoreCase(String prefix);
}
