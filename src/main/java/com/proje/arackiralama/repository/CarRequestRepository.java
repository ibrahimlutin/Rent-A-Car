package com.proje.arackiralama.repository;

import com.proje.arackiralama.model.CarRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface CarRequestRepository extends JpaRepository<CarRequest,Integer> {
}
