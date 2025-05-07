package com.proje.arackiralama.service;

import com.proje.arackiralama.dao.CarDao;
import com.proje.arackiralama.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarDao carDao;

    public void addCar(Car car) {
        carDao.saveCar(car);
    }

    public void deleteById(int id) {
        carDao.deleteCarById(id);
    }

    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    public Car getCar(int id) {
        return carDao.getCarById(id);
    }

    // ✅ Eksik fonksiyon: Belirli koltuk sayısına göre ve sadece müsait araçları getirir
    public List<Car> getAvailableCarsByCapacity(int seatingCapacity) {
        return carDao.getAvailableCarsBySeatingCapacity(seatingCapacity);
    }

    // ✅ Eksik fonksiyon: Araç modeline göre (başlayan) arama
    public List<Car> searchByModel(String keyword) {
        return carDao.searchCarsByModelPrefix(keyword);
    }
}
