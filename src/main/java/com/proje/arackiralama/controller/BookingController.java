package com.proje.arackiralama.controller; // Controller sınıflarının bulunduğu paket

// Gerekli sınıflar ve servisler import ediliyor
import com.proje.arackiralama.model.Booking; // Booking modeli (rezervasyon verileri)
import com.proje.arackiralama.model.Car; // Car modeli (araç bilgileri)
import com.proje.arackiralama.service.BookingService; // Rezervasyon işlemleri servisi
import com.proje.arackiralama.service.CarService; // Araç işlemleri servisi
import org.springframework.beans.factory.annotation.Autowired; // Otomatik bağımlılık enjeksiyonu için
import org.springframework.stereotype.Controller; // Bu sınıfın bir controller olduğunu belirtir
import org.springframework.ui.Model; // View'a veri göndermek için kullanılır
import org.springframework.ui.ModelMap; // Alternatif Model sınıfı (map tabanlı)
import org.springframework.web.bind.annotation.*; // Web istekleri için gereken anotasyonlar

import java.util.List; // Liste veri yapısı
import java.util.Optional; // Null olabilecek nesneler için güvenli sarmalayıcı

@Controller // Bu sınıfın bir Spring Controller olduğunu belirtir
public class BookingController {

    @Autowired // Spring, BookingService bean’ini otomatik olarak buraya enjekte eder
    private BookingService bookingService;

    @Autowired // Spring, CarService bean’ini otomatik olarak buraya enjekte eder
    private CarService carService;

    // 🔽 Tüm rezervasyonları listelemek için kullanılır
    @RequestMapping("/list-bookings") // Bu URL'ye gelen istekleri karşılar
    public String listAllBookings(ModelMap modelMap) {
        List<Booking> bookings = bookingService.getAllBookings(); // Tüm rezervasyonları al
        modelMap.put("bookings", bookings); // Model'e "bookings" adlı veri eklenir
        return "listBookings"; // "listBookings.html" sayfasına yönlendirilir
    }

    // 🔽 Bir rezervasyonu tamamlama işlemi (admin panelde kullanılabilir)
    @RequestMapping("/complete-booking") // Bu URL'ye gelen istekleri karşılar
    public String completeBooking(@RequestParam int bookingId, Model model) {
        Optional<Booking> optionalBooking = bookingService.getBooking(bookingId); // ID'ye göre rezervasyonu getir

        if (optionalBooking.isEmpty()) { // Eğer rezervasyon bulunamazsa
            model.addAttribute("errorMessage", "Rezervasyon bulunamadı. ID: " + bookingId);
            return "booking-list"; // Hata mesajı ile birlikte rezervasyon listesi ekranına dön
        }

        Booking booking = optionalBooking.get(); // Rezervasyon nesnesi elde edilir
        Car car = carService.getCar(booking.getCarId()); // Rezervasyondaki aracı bul

        if (car == null) { // Eğer araç veritabanında bulunamazsa
            model.addAttribute("errorMessage", "Araca ulaşılamadı. ID: " + booking.getCarId());
            return "booking-list"; // Hata mesajı ile birlikte geri dön
        }

        // Aracı tekrar müsait hale getir (boşalt)
        car.setAvailableForBooking(true); // Kiralamaya açık hale getir
        car.setDriverId(null); // Atanmış sürücüyü kaldır
        car.setBookingDate(null); // Rezervasyon tarihini temizle
        carService.addCar(car); // Araç güncellemesini kaydet

        bookingService.deleteBooking(bookingId); // Rezervasyonu sil
        return "redirect:/list-bookings"; // Rezervasyon listesini yeniden yükle
    }

    // 🔽 Kullanıcı adına göre rezervasyonları filtreleme
    @GetMapping("/search-bookings") // GET isteği ile yapılan aramalarda kullanılır
    public String searchBookingsByUsername(@RequestParam("username") String username, ModelMap modelMap) {
        if (username == null || username.trim().isEmpty()) {
            // Arama kutusu boşsa tüm rezervasyonları getir
            modelMap.put("bookings", bookingService.getAllBookings());
        } else {
            // Girilen kullanıcı adına göre filtreleme yap
            modelMap.put("bookings", bookingService.getByUsername(username));
        }
        return "listBookings"; // Sonuçları aynı liste ekranında göster
    }
}
