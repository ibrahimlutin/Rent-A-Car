# Arackiralama - Car Rental System / Araç Kiralama Sistemi

---

## English

### Project Overview

This is a Car Rental System developed as a school project. The application provides a platform for managing vehicle rentals, allowing users to browse available cars, make bookings, and enabling administrators to manage the car fleet, drivers, and user requests. It demonstrates the use of Java and the Spring Boot framework to build a functional web application.

This project was developed to apply and showcase foundational software engineering principles, including MVC architecture, RESTful API design (for backend logic), and web development fundamentals.

### Key Features

* **User Authentication & Management:** Secure user registration and login functionality using Spring Security.
* **Car Fleet Management:** Administrators can add new cars to the system and view existing car listings.
* **Driver Management:** Functionality to add, view, and edit driver information.
* **Booking System:** Users can view available cars and make bookings for specific periods.
* **Car Request System:** Users can submit requests for specific types of cars if not immediately available.
* **Error Handling:** Basic error handling for a smoother user experience.
* **Web Interface:** User-friendly interface built with Thymeleaf for interacting with the system.

### Technologies Used

* **Backend:** Java, Spring Boot
    * Spring MVC: For building the web application layer.
    * Spring Data JPA: For data persistence and interaction with the database.
    * Spring Security: For authentication and authorization.
* **Frontend:** Thymeleaf (Server-side template engine)
* **Build Tool:** Apache Maven
* **Version Control:** Git
* **Database:** (Assumed, typically an SQL database like H2, PostgreSQL, or MySQL configured via `application.properties`)

### Project Structure

The project follows a standard Spring Boot project structure:

* `src/main/java`: Contains the Java source code.
    * `com.proje.arackiralama.config`: Spring configurations, including security.
    * `com.proje.arackiralama.controller`: Handles incoming web requests.
    * `com.proje.arackiralama.dao`: Data Access Objects (can also be seen as an older pattern, often replaced by direct Repository use).
    * `com.proje.arackiralama.model`: Entity classes representing the data.
    * `com.proje.arackiralama.repository`: Spring Data JPA repositories for database operations.
    * `com.proje.arackiralama.service`: Business logic layer.
* `src/main/resources`: Contains static resources and templates.
    * `static`: For CSS, JavaScript, images.
    * `templates`: Thymeleaf HTML templates.
    * `application.properties`: Application configuration.
* `pom.xml`: Maven project configuration file.

### Setup and Run

1.  **Prerequisites:**
    * Java Development Kit (JDK) 11 or newer.
    * Apache Maven.
    * Git.
    * An SQL database (e.g., H2, PostgreSQL, MySQL) and configure its connection in `src/main/resources/application.properties`.

2.  **Clone the Repository:**
    ```bash
    git clone https://github.com/ibrahimlutin/Rent-A-Car.git
    cd arackiralama
    ```

3.  **Configure Database:**
    * Open `src/main/resources/application.properties`.
    * Update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties according to your database setup.
    * Ensure `spring.jpa.hibernate.ddl-auto` is set appropriately (e.g., `update` or `create-drop` for development).

4.  **Build and Run the Application:**
    ```bash
    ./mvnw spring-boot:run
    ```
    Alternatively, you can import the project into an IDE like IntelliJ IDEA or Eclipse and run the `ArackiralamaApplication` main class.

5.  **Access the Application:**
    Open your web browser and navigate to `http://localhost:8080` (or the port configured in `application.properties`).

### Looking for Internship Opportunities

I developed this project as part of my academic curriculum, gaining valuable experience in full-stack Java development with the Spring Boot framework. I am enthusiastic about applying these skills in a professional environment and am currently seeking internship opportunities where I can contribute to real-world projects and continue to learn and grow as a software developer.

---

## Türkçe

### Projeye Genel Bakış

Bu, bir okul projesi olarak geliştirilmiş bir Araç Kiralama Sistemi'dir. Uygulama, kullanıcıların mevcut araçlara göz atmasına, rezervasyon yapmasına ve yöneticilerin araç filosunu, sürücüleri ve kullanıcı taleplerini yönetmesine olanak tanıyan bir araç kiralama yönetim platformu sunar. Java ve Spring Boot çatısını kullanarak işlevsel bir web uygulaması oluşturmayı göstermektedir.

Bu proje, MVC mimarisi, RESTful API tasarımı (arka uç mantığı için) ve web geliştirme temelleri dahil olmak üzere temel yazılım mühendisliği prensiplerini uygulamak ve sergilemek amacıyla geliştirilmiştir.

### Temel Özellikler

* **Kullanıcı Kimlik Doğrulama ve Yönetimi:** Spring Security kullanılarak güvenli kullanıcı kaydı ve giriş işlevselliği.
* **Araç Filosu Yönetimi:** Yöneticiler sisteme yeni araçlar ekleyebilir ve mevcut araç listelerini görüntüleyebilir.
* **Sürücü Yönetimi:** Sürücü bilgilerini ekleme, görüntüleme ve düzenleme işlevselliği.
* **Rezervasyon Sistemi:** Kullanıcılar mevcut araçları görüntüleyebilir ve belirli dönemler için rezervasyon yapabilir.
* **Araç Talep Sistemi:** Kullanıcılar, hemen mevcut olmayan belirli araç türleri için talepte bulunabilirler.
* **Hata Yönetimi:** Daha sorunsuz bir kullanıcı deneyimi için temel hata yönetimi.
* **Web Arayüzü:** Sistemle etkileşim için Thymeleaf ile oluşturulmuş kullanıcı dostu arayüz.

### Kullanılan Teknolojiler

* **Backend (Arka Uç):** Java, Spring Boot
    * Spring MVC: Web uygulama katmanını oluşturmak için.
    * Spring Data JPA: Veri kalıcılığı ve veritabanı ile etkileşim için.
    * Spring Security: Kimlik doğrulama ve yetkilendirme için.
* **Frontend (Ön Uç):** Thymeleaf (Sunucu taraflı şablon motoru)
* **Build Aracı:** Apache Maven
* **Versiyon Kontrolü:** Git
* **Veritabanı:** (Varsayılan, genellikle `application.properties` üzerinden yapılandırılan H2, PostgreSQL veya MySQL gibi bir SQL veritabanı)

### Proje Yapısı

Proje, standart bir Spring Boot proje yapısını takip eder:

* `src/main/java`: Java kaynak kodlarını içerir.
    * `com.proje.arackiralama.config`: Güvenlik dahil Spring yapılandırmaları.
    * `com.proje.arackiralama.controller`: Gelen web isteklerini işler.
    * `com.proje.arackiralama.dao`: Veri Erişim Nesneleri (eski bir model olarak da görülebilir, genellikle doğrudan Repository kullanımı ile değiştirilir).
    * `com.proje.arackiralama.model`: Veriyi temsil eden varlık (entity) sınıfları.
    * `com.proje.arackiralama.repository`: Veritabanı işlemleri için Spring Data JPA repository'leri.
    * `com.proje.arackiralama.service`: İş mantığı katmanı.
* `src/main/resources`: Statik kaynakları ve şablonları içerir.
    * `static`: CSS, JavaScript, resimler için.
    * `templates`: Thymeleaf HTML şablonları.
    * `application.properties`: Uygulama yapılandırması.
* `pom.xml`: Maven proje yapılandırma dosyası.

### Kurulum ve Çalıştırma

1.  **Ön Koşullar:**
    * Java Development Kit (JDK) 11 veya daha yenisi.
    * Apache Maven.
    * Git.
    * Bir SQL veritabanı (örneğin H2, PostgreSQL, MySQL) ve `src/main/resources/application.properties` dosyasında bağlantısının yapılandırılması.

2.  **Depoyu Klonlayın:**
    ```bash
    git clone https://github.com/ibrahimlutin/Rent-A-Car.git
    cd arackiralama
    ```

3.  **Veritabanını Yapılandırın:**
    * `src/main/resources/application.properties` dosyasını açın.
    * `spring.datasource.url`, `spring.datasource.username` ve `spring.datasource.password` özelliklerini veritabanı kurulumunuza göre güncelleyin.
    * `spring.jpa.hibernate.ddl-auto` özelliğinin uygun şekilde ayarlandığından emin olun (geliştirme için örn. `update` veya `create-drop`).

4.  **Uygulamayı Derleyin ve Çalıştırın:**
    ```bash
    ./mvnw spring-boot:run
    ```
    Alternatif olarak, projeyi IntelliJ IDEA veya Eclipse gibi bir IDE'ye aktarabilir ve `ArackiralamaApplication` ana sınıfını çalıştırabilirsiniz.

5.  **Uygulamaya Erişin:**
    Web tarayıcınızı açın ve `http://localhost:8080` adresine (veya `application.properties` dosyasında yapılandırılan porta) gidin.

### Staj Fırsatları Arıyorum

Bu projeyi akademik müfredatımın bir parçası olarak geliştirdim ve Spring Boot çatısı ile tam yığın (full-stack) Java geliştirme konusunda değerli deneyimler kazandım. Bu becerileri profesyonel bir ortamda uygulamaya hevesliyim ve şu anda gerçek dünya projelerine katkıda bulunabileceğim, bir yazılım geliştiricisi olarak öğrenmeye ve büyümeye devam edebileceğim staj fırsatları arıyorum.

---

### Contact / İletişim

* **Name / İsim:** [İbrahim Halil Lutin]
* **Email / E-posta:** [ibrahimlutin@gmail.com]
* **LinkedIn:** [https://www.linkedin.com/in/ibrahim-halil-lutin-975122293/]
* **GitHub:** [https://github.com/ibrahimlutin]
