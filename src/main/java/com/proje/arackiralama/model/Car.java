package com.proje.arackiralama.model;

import jakarta.persistence.*; // JPA (Java Persistence API) için gerekli anotasyonları içerir
import java.time.LocalDate; // Tarih bilgisini temsil etmek için kullanılır

// Bu sınıfın bir JPA Entity (veritabanı tablosu) olduğunu belirtir
@Entity
public class Car {

    // Primary key (benzersiz ID) alanı olduğunu belirtir
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Araç modeli (örneğin: Toyota Corolla)
    private String model;

    // Araç rengi (örneğin: Beyaz)
    private String color;

    // Aracın kaç kişilik olduğunu belirten alan
    private Integer seatingCapacity;

    // Araç şu anda rezerve edilebilir mi?
    private boolean availableForBooking;

    // Bu aracı kullanan sürücünün ID’si
    private Integer driverId;

    // Rezervasyon tarihi (boş olabilir)
    @Column(nullable = true)
    private LocalDate bookingDate;  // yeni alan

    // Getter - bookingDate alanını dışarıya verir
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    // Setter - bookingDate alanını ayarlar
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Büyük boyutlu veriler (örneğin resim) için kullanılır
    @Lob
    @Column(columnDefinition = "LONGBLOB") // MySQL'de uzun byte dizileri için tanım
    private byte[] imageData; // 👈 Veritabanında resmi byte olarak saklayacak alan

    // --- Constructor (yapıcı metodlar) ---

    // Parametresiz varsayılan constructor (JPA için zorunludur)
    public Car() {}

    // Tüm alanları içeren constructor (image hariç bookingDate dahil değil)
    public Car(Integer id, String model, String color, Integer seatingCapacity,
               boolean availableForBooking, Integer driverId, byte[] imageData) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.seatingCapacity = seatingCapacity;
        this.availableForBooking = availableForBooking;
        this.driverId = driverId;
        this.imageData = imageData;
    }

    // --- Getter & Setter metodları ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public boolean isAvailableForBooking() {
        return availableForBooking;
    }

    public void setAvailableForBooking(boolean availableForBooking) {
        this.availableForBooking = availableForBooking;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    // Nesne bilgilerini String olarak döndürür (genellikle debug amaçlı)
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", availableForBooking=" + availableForBooking +
                ", driverId=" + driverId +
                ", imageData=" + (imageData != null ? "[byte array]" : "null") +
                '}';
    }
}
