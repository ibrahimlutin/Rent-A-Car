package com.proje.arackiralama.controller; // Controller sınıflarını barındıran package

// Gerekli sınıf ve Spring bileşenleri import ediliyor
import com.proje.arackiralama.model.Driver; // Driver model sınıfı
import com.proje.arackiralama.service.DriverService; // Driver işlemlerini yürüten servis
import org.springframework.beans.factory.annotation.Autowired; // Otomatik bağımlılık enjeksiyonu için
import org.springframework.stereotype.Controller; // Bu sınıfın bir controller olduğunu belirtir
import org.springframework.ui.Model; // HTML sayfalarına veri aktarmak için
import org.springframework.ui.ModelMap; // Alternatif veri aktarım sınıfı
import org.springframework.web.bind.annotation.*; // Mapping anotasyonları için

import java.util.List;

@Controller // Spring MVC Controller olarak işaretlenmiştir
public class DriverController {

    @Autowired // DriverService otomatik olarak enjekte edilir
    private DriverService driverService;

    // 🔽 Tüm şoförleri listeleyen yöntem
    @RequestMapping("list-drivers") // Bu endpoint'e gelen istekleri karşılar
    public String listAllDrivers(ModelMap modelMap) {
        List<Driver> drivers = driverService.getAllDrivers(); // Tüm şoförler getirilir
        modelMap.put("drivers", drivers); // View'a gönderilmek üzere modele eklenir
        return "listDrivers"; // listDrivers.html dosyasına yönlendirilir
    }

    // 🔽 Belirli bir şoförün bilgilerini düzenlemek için formu gösterir
    @GetMapping("/edit-driver")
    public String showEditDriverPage(@RequestParam int id, ModelMap modelMap) {
        Driver driver = driverService.getDriver(id); // ID’ye göre şoför getirilir

        if (driver == null) { // Eğer şoför bulunamazsa hata mesajı verilir
            modelMap.put("errorMessage", "Sürücü bulunamadı. ID: " + id);
            return "driver-list"; // Alternatif olarak kullanıcıyı liste ekranına yönlendir
        }

        modelMap.put("driver", driver); // Bulunan sürücü modele eklenir
        return "editDriver"; // editDriver.html sayfasına yönlendirilir
    }

    // 🔽 Güncellenen şoför bilgilerini kaydeder
    @PostMapping("/update-driver")
    public String updateDriver(@ModelAttribute Driver updatedDriver, Model model) {
        Driver existing = driverService.getDriver(updatedDriver.getId()); // Güncellenmek istenen şoför veritabanından çekilir

        if (existing == null) { // Eğer böyle bir şoför yoksa hata döndür
            model.addAttribute("errorMessage", "Güncellenmek istenen sürücü bulunamadı.");
            model.addAttribute("driver", updatedDriver); // Form yeniden doldurulmuş şekilde gösterilir
            return "editDriver"; // Tekrar düzenleme sayfasına dön
        }

        existing.setUsername(updatedDriver.getUsername()); // Yeni kullanıcı adı ayarlanır
        driverService.saveDriver(existing); // Güncellenmiş veri kaydedilir

        return "redirect:/list-drivers"; // Şoför listesini yeniden yükle
    }
}
