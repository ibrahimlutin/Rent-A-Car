package com.proje.arackiralama.service;



import com.proje.arackiralama.model.User;
import com.proje.arackiralama.repository.UserRepository;
import com.proje.arackiralama.security.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Spring tarafından otomatik yönetilecek bir servis bileşenidir
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired // Spring, UserRepository örneğini buraya otomatik olarak enjekte eder
    private UserRepository userRepository;


    @Override // Spring Security login sırasında bu metodu otomatik olarak çağırır
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Veritabanında girilen kullanıcı adına sahip kullanıcıyı bulmaya çalış
        User user = userRepository.findByUsername(username);

        // Eğer kullanıcı bulunamazsa, uygun hata fırlatılır (login başarısız olur)
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        // Kullanıcı bulunduysa, onu CustomUserDetails nesnesine sararak geri döneriz.
        // Bu nesne Spring Security'nin anlayacağı formatta şifre, kullanıcı adı ve yetki bilgilerini içerir.
        return new CustomUserDetails(user);
    }
}




