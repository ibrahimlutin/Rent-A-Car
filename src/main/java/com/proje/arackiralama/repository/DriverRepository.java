package com.proje.arackiralama.repository;


import com.proje.arackiralama.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Integer> {

    Driver findByTcNo(String tcNo);
}
