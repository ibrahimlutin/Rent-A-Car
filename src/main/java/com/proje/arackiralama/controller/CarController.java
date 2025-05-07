package com.proje.arackiralama.controller; // Controller sınıfı, bu paket içinde yer alıyor

// Gerekli model ve servis sınıfları import ediliyor
import com.proje.arackiralama.model.Booking;
import com.proje.arackiralama.model.Car;
import com.proje.arackiralama.service.BookingService;
import com.proje.arackiralama.service.CarService;

import org.springframework.beans.factory.annotation.Autowired; // Spring bağımlılık enjeksiyonu için
import org.springframework.http.*; // HTTP response oluşturmak için gerekli sınıflar
import org.springframework.stereotype.Controller; // Bu sınıfın bir Spring MVC Controller olduğunu belirtir
import org.springframework.ui.Model; // Thymeleaf template motoruna veri taşımak için
import org.springframework.ui.ModelMap; // Alternatif model sınıfı
import org.springframework.web.bind.annotation.*; // Controller'da kullanılan anotasyonlar
import org.springframework.web.multipart.MultipartFile; // Dosya yüklemek için kullanılan sınıf

import java.util.ArrayList;
import java.util.List;

@Controller // Bu sınıf bir Controller'dır, URL isteklerini karşılar
public class CarController {

    @Autowired // Spring, CarService bean'ini otomatik olarak enjekte eder
    private CarService carService;

    @Autowired // BookingService de aynı şekilde otomatik enjekte edilir
    private BookingService bookingService;

    // 🔽 Tüm araçları ve onlara ait rezervasyonları listele
    @RequestMapping("list-cars")
    public String listAllCars(ModelMap modelMap) {
        List<Car> cars = carService.getAllCars(); // Tüm araçları getir
        List<Booking> bookings = new ArrayList<>(); // Her araca karşılık gelen rezervasyon listesi

        for (Car car : cars) {
            Booking booking = bookingService.getByCarId(car.getId()).orElse(null); // Araç için rezervasyon varsa al
            bookings.add(booking); // Her araç için bir rezervasyon (null olabilir) ekleniyor
        }

        modelMap.put("bookings", bookings); // Booking'ler view'a gönderilir
        modelMap.put("cars", cars); // Araçlar view'a gönderilir
        return "listCars"; // listCars.html sayfasına yönlendirilir
    }

    // 🔽 Kullanıcının koltuk sayısına göre müsait araçları listelemesi
    @RequestMapping("list-available-cars")
    public String listAllAvailableCarsForBooking(@RequestParam int seatingCapacity, ModelMap modelMap) {
        List<Car> cars = carService.getAvailableCarsByCapacity(seatingCapacity); // Koltuk sayısına göre uygun araçlar
        modelMap.put("cars", cars); // View'a gönder
        return "listCarsAvailableForBooking"; // Uygun araçları gösteren sayfa
    }

    // 🔽 Yeni araç ekleme formunu göster
    @RequestMapping(value = "add-car", method = RequestMethod.GET)
    public String showNewCarPage(Car car) {
        return "car"; // car.html form sayfasına yönlendirilir
    }

    // 🔽 Yeni araç ekleme işlemi (form submit edildiğinde çalışır)
    @RequestMapping(value = "add-car", method = RequestMethod.POST)
    public String addNewCar(@ModelAttribute Car car,
                            @RequestParam("imageFile") MultipartFile file, // Görsel dosyası alınır
                            Model model) throws Exception {

        // Koltuk kapasitesi kontrolü (2, 4 veya 7 olmalı)
        int capacity = car.getSeatingCapacity();
        if (capacity != 2 && capacity != 4 && capacity != 7) {
            model.addAttribute("errorMessage", "Koltuk kapasitesi sadece 2, 4 veya 7 olabilir.");
            model.addAttribute("car", car); // Form verisi geri doldurulsun
            return "car"; // Hata durumunda form ekranına dön
        }

        car.setAvailableForBooking(true); // Yeni eklenen araç kiralamaya uygun olsun

        // Görsel dosya boş değilse byte verisi olarak kaydet
        if (!file.isEmpty()) {
            car.setImageData(file.getBytes());
        }

        carService.addCar(car); // Araç servisiyle veritabanına ekle
        return "redirect:/list-cars"; // Araç listesini yenile
    }

    // 🔽 Araç modeliyle arama yapma
    @GetMapping("/search-cars")
    public String searchCars(@RequestParam(value = "keyword", required = false) String keyword, ModelMap modelMap) {
        List<Car> cars;
        List<Booking> bookings = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty() && keyword.length() >= 2) {
            cars = carService.searchByModel(keyword.trim()); // Model adına göre filtrele
        } else if (keyword != null && keyword.trim().length() < 2) {
            cars = new ArrayList<>(); // Kısa kelime ile arama yapılmaz
        } else {
            cars = carService.getAllCars(); // Tüm araçlar gösterilir
        }

        // Her araç için rezervasyon getir (varsa)
        for (Car car : cars) {
            Booking booking = bookingService.getByCarId(car.getId()).orElse(null);
            bookings.add(booking);
        }

        modelMap.put("cars", cars);
        modelMap.put("bookings", bookings);
        return "listCars"; // listCars.html sayfası gösterilir
    }

    // 🔽 Belirli bir aracı sil
    @RequestMapping(value = "delete-car")
    public String deleteCar(@RequestParam int id) {
        carService.deleteById(id); // ID ile sil
        return "redirect:/list-cars"; // Listeye geri dön
    }

    // 🔽 Veritabanındaki araç resmini kullanıcıya göstermek için binary veri döndür
    @GetMapping("/car-image/{id}")
    @ResponseBody // JSON yerine doğrudan byte[] döner
    public ResponseEntity<byte[]> getCarImage(@PathVariable int id) {
        Car car = carService.getCar(id); // Aracı getir
        if (car == null || car.getImageData() == null) {
            return ResponseEntity.notFound().build(); // Resim yoksa 404
        }

        HttpHeaders headers = new HttpHeaders(); // HTTP header tanımı
        headers.setContentType(MediaType.IMAGE_JPEG); // JPEG olarak belirt
        return new ResponseEntity<>(car.getImageData(), headers, HttpStatus.OK); // Resmi ve header'ları dön
    }

    // 🔽 Kullanıcı için araç talep formunu göster
    @GetMapping("/car-request-form")
    public String showCarRequestForm(@RequestParam("carId") int carId, ModelMap model) {
        model.put("carId", carId); // carId'yi forma gönder
        return "carRequestForm"; // formun HTML sayfası
    }
}
