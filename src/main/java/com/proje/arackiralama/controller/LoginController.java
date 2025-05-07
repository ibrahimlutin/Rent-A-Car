package com.proje.arackiralama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Bu sınıf, kullanıcı giriş (login) sayfasını kontrol eden Spring MVC Controller'dır
@Controller
public class LoginController {

    // "/login" adresine yapılan GET isteğinde çalışır
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html Thymeleaf şablonunu döndürür (resources/templates/login.html)
    }
}
