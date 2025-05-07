package com.proje.arackiralama.controller; // Controller sınıfı bu pakette tanımlanmış

// Gerekli model ve servis sınıfları import ediliyor
import com.proje.arackiralama.model.*;
import com.proje.arackiralama.service.BookingService;
import com.proje.arackiralama.service.CarRequestService;
import com.proje.arackiralama.service.CarService;
import com.proje.arackiralama.service.DriverService;

import org.springframework.beans.factory.annotation.Autowired; // Spring’in otomatik bean enjeksiyonu için
import org.springframework.stereotype.Controller; // Sınıfın bir controller olduğunu belirtir
import org.springframework.ui.Model; // View’a veri aktarmak için
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*; // Mapping anotasyonları için
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Redirect sırasında mesaj taşıma için

import java.time.LocalDate; // Tarih bilgisi için
import java.util.List;
import java.util.Optional;

@Controller // Spring MVC Controller olarak işaretlenir
public class CarRequestController {

    // Gerekli servisler enjekte ediliyor
    @Autowired
    private CarRequestService carRequestService;

    @Autowired
    private CarService carService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private BookingService bookingService;

    // 🔽 Talebi onayla → Araç ve sürücü atanır, rezervasyon oluşturulur
    @RequestMapping("approve-request")
    public String approveDriverRequest(@RequestParam int requestId,
                                       @RequestParam int driverId,
                                       @RequestParam int carId,
                                       Model model) {

        Driver driver = driverService.getDriver(driverId); // Sürücü bilgisi çekilir
        if (driver == null) {
            model.addAttribute("errorMessage", "Seçilen sürücü bulunamadı. ID: " + driverId);
            return "car-requests"; // Hata varsa tekrar form ekranına dön
        }

        Car car = carService.getCar(carId); // Araç bilgisi çekilir
        if (car == null) {
            model.addAttribute("errorMessage", "Seçilen araç bulunamadı. ID: " + carId);
            return "car-requests";
        }

        Optional<CarRequest> optionalRequest = carRequestService.getRequestById(requestId); // Talep bulunur
        if (optionalRequest.isEmpty()) {
            model.addAttribute("errorMessage", "Araç talebi bulunamadı. ID: " + requestId);
            return "car-requests";
        }

        // 🔄 Talep güncellenir
        CarRequest carRequest = optionalRequest.get();
        carRequest.setRequestStatus("ONAYLANDI");
        carRequestService.saveRequest(carRequest);

        // 🔄 Araç güncellenir
        car.setDriverId(driverId);
        car.setAvailableForBooking(false); // Artık kiralanamaz
        car.setBookingDate(LocalDate.now()); // Rezervasyon tarihi bugünün tarihi olur
        carService.addCar(car);

        // 🔄 Sürücü güncellenir
        driver.setAssignedCarId(carId);
        driverService.saveDriver(driver);

        // 🔄 Booking (rezervasyon) oluşturulur
        Booking booking = new Booking(carId, driverId, driver.getUsername(), LocalDate.now());
        bookingService.saveBooking(booking);

        return "redirect:/list-car-requests"; // Talepler listesine geri dön
    }

    // 🔽 Talebi reddet
    @RequestMapping("reject-request")
    public String rejectDriverRequest(@RequestParam int requestId, Model model) {

        Optional<CarRequest> optionalRequest = carRequestService.getRequestById(requestId);
        if (optionalRequest.isEmpty()) {
            model.addAttribute("errorMessage", "Talep bulunamadı. ID: " + requestId);
            return "car-requests"; // Hata varsa ekrana dön
        }

        // Talep durumu REDDEDİLDİ yapılır
        CarRequest carRequest = optionalRequest.get();
        carRequest.setRequestStatus("REDDEDİLDİ");
        carRequestService.saveRequest(carRequest);

        return "redirect:/list-car-requests"; // Listeye yönlendir
    }

    // 🔽 Tüm talepleri sil (admin işlemi)
    @RequestMapping("delete-car-requests")
    public String deleteAllCarRequests() {
        carRequestService.deleteAllRequests(); // Tüm kayıtlar silinir
        return "redirect:/list-car-requests";
    }

    // 🔽 Tüm talepleri listele (admin sayfası gibi)
    @RequestMapping("list-car-requests")
    public String listAllCarRequests(ModelMap modelMap) {
        List<CarRequest> carRequests = carRequestService.getAllRequests(); // Tüm talepler çekilir
        modelMap.put("car_requests", carRequests); // View’a gönderilir
        return "listCarRequests"; // listCarRequests.html sayfası
    }

    // 🔽 Kullanıcı formdan yeni araç talep ettiğinde çalışır
    @PostMapping("/submit-car-request")
    public String submitCarRequest(@RequestParam int carId,
                                   @RequestParam String username,
                                   @RequestParam String tcNo,
                                   RedirectAttributes redirectAttributes) {

        Driver driver = driverService.getDriverByTcNo(tcNo); // TC ile sürücü araması yapılır

        if (driver == null) {
            // Eğer sürücü yoksa yeni bir sürücü oluşturulur
            driver = new Driver();
            driver.setUsername(username);
            driver.setTcNo(tcNo);
            driverService.saveDriver(driver);
        }

        // Sürücünün mevcutta bir booking’i varsa tekrar talep oluşturamaz
        boolean alreadyHasBooking = bookingService.getByDriverId(driver.getId()).isPresent();
        if (alreadyHasBooking) {
            redirectAttributes.addFlashAttribute("error", "Bu sürücü zaten bir araç kiralamış.");
            return "redirect:/car-request-form?carId=" + carId;
        }

        // Talep nesnesi oluşturulur
        CarRequest request = new CarRequest();
        request.setCarId(carId);
        request.setDriverId(driver.getId());
        request.setRequestStatus("BEKLİYOR"); // Başlangıç durumu

        carRequestService.saveRequest(request); // Talep kaydedilir
        return "redirect:/"; // Ana sayfaya yönlendirilir
    }
}
