package com.proje.arackiralama.security; // Güvenlik sınıflarının yer aldığı paket

// Gerekli sınıflar import ediliyor
import com.proje.arackiralama.model.User; // Uygulamanın kendi User model sınıfı
import org.springframework.security.core.GrantedAuthority; // Yetki (rol) temsil eden arayüz
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Basit bir yetki sınıfı (örn: "ADMIN")
import org.springframework.security.core.userdetails.UserDetails; // Spring Security'nin kullanıcı detayı arayüzü
import org.springframework.stereotype.Component; // Spring bileşeni olarak işaretlenir

import java.util.Collection; // Birden fazla yetki için koleksiyon
import java.util.Collections; // Tek elemanlı liste üretmek için

@Component // Bu sınıf, Spring konteynerinde otomatik tanınır
public class CustomUserDetails implements UserDetails {

    private User user; // Uygulamaya özel kullanıcı nesnesi (veritabanından gelen)

    public CustomUserDetails() {} // Parametresiz boş kurucu

    public CustomUserDetails(User user) {
        super(); // Üst sınıf kurucusu çağrılır (zorunlu değil)
        this.user = user; // Kullanıcı atanır
    }

    // 🔽 Kullanıcının rollerini (yetkilerini) döner
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
        // Örn: "ROLE_ADMIN" gibi string bir rolü yetki nesnesine çevirir
    }

    // 🔽 Sisteme giriş yapan kullanıcı ID'sini almak için ek metot (Spring Security kullanmaz ama projede işine yarayabilir)
    public int getId() {
        return user.getId();
    }

    // 🔽 Kullanıcının şifresi
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 🔽 Kullanıcının kullanıcı adı (giriş yaparken kullanılır)
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 🔽 Hesap süresi dolmuş mu? (false olursa giriş reddedilir)
    @Override
    public boolean isAccountNonExpired() {
        return true; // Hesap süresi dolmamış
    }

    // 🔽 Hesap kilitli mi? (false olursa giriş reddedilir)
    @Override
    public boolean isAccountNonLocked() {
        return true; // Hesap kilitli değil
    }

    // 🔽 Şifre süresi dolmuş mu?
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Şifre geçerli
    }

    // 🔽 Kullanıcı aktif mi?
    @Override
    public boolean isEnabled() {
        return true; // Kullanıcı etkin
    }
}
