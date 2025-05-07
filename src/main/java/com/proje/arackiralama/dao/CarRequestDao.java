package com.proje.arackiralama.dao; // DAO sınıflarının yer aldığı paket

// Gerekli sınıflar ve anotasyonlar import ediliyor
import com.proje.arackiralama.model.CarRequest; // Araç talebi model sınıfı
import com.proje.arackiralama.repository.CarRequestRepository; // Spring Data JPA repository arayüzü
import org.springframework.beans.factory.annotation.Autowired; // Otomatik bağımlılık enjeksiyonu için
import org.springframework.stereotype.Repository; // Spring'e bu sınıfın repository olduğunu belirtir

import java.util.List;
import java.util.Optional;

@Repository // Spring bu sınıfı otomatik olarak bir repository (bean) olarak tanır
public class CarRequestDao {

    @Autowired // Spring, CarRequestRepository örneğini otomatik olarak enjekte eder
    private CarRequestRepository carRequestRepository;

    // 🔽 Tüm araç taleplerini getirir
    public List<CarRequest> getAllRequests() {
        return carRequestRepository.findAll(); // Tüm kayıtları listeler
    }

    // 🔽 ID ile belirli bir araç talebini getirir
    public Optional<CarRequest> getById(int id) {
        return carRequestRepository.findById(id); // Optional olarak döner (boş olabilir)
    }

    // 🔽 Yeni bir talep kaydeder veya mevcut olanı günceller
    public void save(CarRequest request) {
        carRequestRepository.save(request); // insert veya update işlemi yapar
    }

    // 🔽 Tüm araç talebi kayıtlarını siler
    public void deleteAll() {
        carRequestRepository.deleteAll(); // Veritabanındaki tüm CarRequest kayıtlarını siler
    }
}
