package com.proje.arackiralama.repository;

import com.proje.arackiralama.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
