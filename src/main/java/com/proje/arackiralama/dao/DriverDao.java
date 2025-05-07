package com.proje.arackiralama.dao; // DAO sınıflarının bulunduğu paket

// Gerekli sınıflar ve anotasyonlar import ediliyor
import com.proje.arackiralama.model.Driver; // Driver (sürücü) model sınıfı
import com.proje.arackiralama.repository.DriverRepository; // Spring Data JPA ile veritabanı işlemleri
import org.springframework.beans.factory.annotation.Autowired; // Otomatik bağımlılık enjeksiyonu
import org.springframework.stereotype.Repository; // Spring’e bunun bir Repository sınıfı olduğunu belirtir

import java.util.List;

@Repository // Bu sınıf, Spring tarafından bir repository bileşeni olarak yönetilir
public class DriverDao {

    @Autowired // Spring, DriverRepository örneğini buraya enjekte eder
    private DriverRepository driverRepository;

    // 🔽 Tüm sürücüleri veritabanından getirir
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll(); // Tüm Driver kayıtlarını döner
    }

    // 🔽 ID'si verilen sürücüyü getirir, yoksa null döner
    public Driver getDriverById(int id) {
        return driverRepository.findById(id).orElse(null); // Optional kullanımı, null kontrolü içerir
    }

    // 🔽 Sürücüyü kaydeder ya da günceller
    public void saveDriver(Driver driver) {
        driverRepository.save(driver); // insert veya update işlemi yapar
    }

    // 🔽 TC Kimlik numarasına göre sürücüyü getirir
    public Driver getDriverByTcNo(String tcNo) {
        return driverRepository.findByTcNo(tcNo); // Custom query — repository içinde tanımlı olmalı
    }
}
