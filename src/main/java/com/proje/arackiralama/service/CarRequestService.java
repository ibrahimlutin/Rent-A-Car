package com.proje.arackiralama.service;

import com.proje.arackiralama.dao.CarRequestDao;
import com.proje.arackiralama.model.CarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarRequestService {

    @Autowired
    private CarRequestDao carRequestDao;

    public List<CarRequest> getAllRequests() {
        return carRequestDao.getAllRequests();
    }

    public Optional<CarRequest> getRequestById(int id) {
        return carRequestDao.getById(id);
    }

    public void saveRequest(CarRequest request) {
        carRequestDao.save(request);
    }

    public void deleteAllRequests() {
        carRequestDao.deleteAll();
    }
}
