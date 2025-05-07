package com.proje.arackiralama.dao; // DAO sınıflarının yer aldığı paket

// Gerekli sınıflar import ediliyor
import com.proje.arackiralama.model.Car; // Araç model sınıfı
import com.proje.arackiralama.repository.CarRepository; // Spring Data JPA repository arayüzü
import org.springframework.beans.factory.annotation.Autowired; // Bağımlılık enjeksiyonu
import org.springframework.stereotype.Repository; // Spring'e bu sınıfın bir Repository olduğunu bildirir

import java.util.List;

@Repository // Spring tarafından otomatik olarak bean olarak tanımlanır
public class CarDao {

    @Autowired // CarRepository otomatik olarak enjekte edilir
    private CarRepository carRepository;

    // 🔽 Yeni araç kaydeder veya mevcut aracı günceller
    public void saveCar(Car car) {
        carRepository.save(car); // insert veya update işlemi yapılır
    }

    // 🔽 Belirtilen ID’ye sahip aracı siler
    public void deleteCarById(int id) {
        carRepository.deleteById(id);
    }

    // 🔽 Tüm araçları veritabanından getirir
    public List<Car> getAllCars() {
        return carRepository.findAll(); // Tüm araçları liste olarak döner
    }

    // 🔽 ID’ye göre bir aracı getirir, yoksa null döner
    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null); // Optional nesne yoksa null döndürülür
    }

    // 🔽 Belirli koltuk sayısına sahip ve kiralanabilir (available) araçları getirir
    public List<Car> getAvailableCarsBySeatingCapacity(int capacity) {
        return carRepository.findBySeatingCapacityAndAvailableForBookingTrue(capacity);
        // Bu metod, CarRepository içinde tanımlanmış olmalı
    }

    // 🔽 Model adı verilen anahtar kelimeyle başlayan araçları getirir (büyük/küçük harf duyarsız)
    public List<Car> searchCarsByModelPrefix(String keyword) {
        return carRepository.findByModelStartingWithIgnoreCase(keyword);
        // Bu da CarRepository içinde tanımlı özel bir sorgudur
    }
}
