package com.proje.arackiralama.service;

import com.proje.arackiralama.dao.DriverDao;
import com.proje.arackiralama.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverDao driverDao;

    public List<Driver> getAllDrivers() {
        return driverDao.getAllDrivers();
    }

    public Driver getDriver(int id) {
        return driverDao.getDriverById(id);
    }

    public void saveDriver(Driver driver) {
        driverDao.saveDriver(driver);
    }

    public Driver getDriverByTcNo(String tcNo) {
        return driverDao.getDriverByTcNo(tcNo);
    }
}
