package com.proje.arackiralama.config; // Bu sınıf, "config" adlı yapılandırma paketinde yer alıyor.

import org.springframework.beans.factory.annotation.Autowired; // Otomatik bağımlılık enjeksiyonu için kullanılır.
import org.springframework.context.annotation.Bean; // Spring'e bir nesnenin bean (bileşen) olduğunu belirtir.
import org.springframework.context.annotation.Configuration; // Bu sınıfın bir yapılandırma sınıfı olduğunu belirtir.
import org.springframework.security.authentication.AuthenticationProvider; // Kimlik doğrulama sağlayıcısı arayüzü.
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; // Veritabanı tabanlı kullanıcı kimlik doğrulaması sağlayan sağlayıcı.
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // HTTP güvenliği yapılandırmak için kullanılır.
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // Web güvenliğini etkinleştiren anotasyon.
import org.springframework.security.core.userdetails.UserDetailsService; // Kullanıcı bilgilerini sağlayan arayüz.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Parola şifreleme için güçlü bir algoritma.
import org.springframework.security.crypto.password.PasswordEncoder; // Şifreleyici arayüzü.
import org.springframework.security.web.SecurityFilterChain; // Güvenlik filtre zincirini tanımlar.

@Configuration // Bu sınıfın bir Spring yapılandırma sınıfı olduğunu belirtir.
@EnableWebSecurity // Web güvenliğini aktif hale getirir.
public class SpringSecurityConfiguration {

    @Autowired // Spring tarafından userDetailsService otomatik olarak enjekte edilir.
    private UserDetailsService userDetailsService; // Kullanıcı bilgilerini sağlayan servis (veritabanından kullanıcıyı çeker).

    @Bean // Bu metodun dönüşü Spring tarafından yönetilen bir bean olacaktır.
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // DAO tabanlı kimlik doğrulama sağlayıcısı tanımlanır.
        provider.setUserDetailsService(userDetailsService); // Kullanıcı bilgileri için özel UserDetailsService kullanılır.
        provider.setPasswordEncoder(new BCryptPasswordEncoder()); // Şifreleme için BCrypt kullanılır.
        return provider; // Sağlayıcıyı geri döndür.
    }

    @Bean // Şifreleyiciyi bir bean olarak tanımlar.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt şifreleme algoritması ile şifreleri şifrele.
    }

    @Bean // SecurityFilterChain konfigürasyonunun Spring tarafından yönetileceğini belirtir.
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> auth // Yetkilendirme kurallarını başlatır.
                        .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                        // Bu yollar herkese açık, login olmadan erişilebilir (statik kaynaklar dahil).

                        // ADMIN yetkisine sahip kullanıcıların erişebileceği yollar.
                        .requestMatchers(
                                "/list-cars",               // Araçları listeleme
                                "/list-drivers",            // Şoförleri listeleme
                                "/list-car-requests",       // Talep edilen araçlar
                                "/delete-car-requests",     // Araç taleplerini silme
                                "/assign-car/**",           // Araba atama
                                "/delete-driver/**",        // Şoför silme
                                "/delete-car/**"            // Araç silme
                        ).hasAuthority("ADMIN")

                        // USER yetkisine sahip kullanıcıların erişebileceği yollar.
                        .requestMatchers(
                                "/list-available-cars/**",  // Müsait araçlar listesi
                                "/book-car/**",             // Araç kiralama
                                "/cancel-car/**"            // Kiralamayı iptal etme
                        ).hasAuthority("USER")

                        .anyRequest().authenticated() // Diğer tüm yollar login gerektirir.
                )
                .formLogin(form -> form
                        .loginPage("/login") // Giriş sayfası için özel endpoint belirlenir.
                        .loginProcessingUrl("/login") // Login formunun action'ı burası olmalı.
                        .defaultSuccessUrl("/", true) // Giriş başarılıysa yönlendirilecek ana sayfa.
                        .failureUrl("/login?error=true") // Giriş başarısız olursa gösterilecek sayfa.
                        .permitAll() // Herkes erişebilir.
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Çıkış işleminin yapılacağı endpoint.
                        .logoutSuccessUrl("/login?logout=true") // Çıkıştan sonra yönlendirilecek URL.
                        .invalidateHttpSession(true) // Oturumu geçersiz kılar.
                        .clearAuthentication(true) // Kimlik doğrulamasını temizler.
                        .deleteCookies("JSESSIONID") // Oturum çerezini siler.
                        .permitAll() // Herkes çıkış yapabilir.
                )
                .csrf(csrf -> csrf.disable()) // CSRF (cross-site request forgery) korumasını kapatır. (Eğer API ya da basit uygulama ise kullanılabilir)
                .headers(headers -> headers.disable()); // HTTP header ayarlarını devre dışı bırakır (örneğin X-Frame-Options gibi).

        return httpSecurity.build(); // SecurityFilterChain nesnesi oluşturulur.
    }
}
