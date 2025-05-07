package com.proje.arackiralama.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

// Bu sınıf, giriş yapan kullanıcı için "welcome" sayfasını yöneten Spring MVC Controller'dır
@Controller
@SessionAttributes({"username", "id"}) // Bu controller'daki "username" ve "id" değerleri session boyunca saklanır
public class WelcomeController {

    // Ana sayfa "/" isteği geldiğinde çalışır (HTTP GET ile)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoWelcomePage(ModelMap model) {
        model.put("username", getLoggedInUsername()); // Giriş yapan kullanıcının adı modele eklenir
        return "welcome"; // welcome.html şablonunu döndürür
    }

    // Spring Security üzerinden aktif kullanıcının adını alır
    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Güvenlik bağlamından oturum bilgisini al
        return authentication.getName(); // Kullanıcının kullanıcı adını döndür
    }

}
