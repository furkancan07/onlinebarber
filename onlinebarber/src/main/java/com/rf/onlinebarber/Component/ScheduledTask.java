package com.rf.onlinebarber.Component;

import com.rf.onlinebarber.Entity.Appointment;
import com.rf.onlinebarber.Entity.Customer;
import com.rf.onlinebarber.Entity.Shop;
import com.rf.onlinebarber.Entity.Token;
import com.rf.onlinebarber.Repository.AppointmentRepository;
import com.rf.onlinebarber.Repository.CustomerRepository;
import com.rf.onlinebarber.Repository.ShopRepository;
import com.rf.onlinebarber.Repository.TokenRepository;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;
    private final TokenRepository tokenRepository;

    // burada her gün saat gece 12 de spring bootun otomatik çalışıp 1 haftadan eski randevuları silme işlemi yapılacak
    @Scheduled(cron = "0 0 0 * * ?") // her gün gece 12
    public void deleteAppointment() {
        LocalDateTime oneWeek = LocalDateTime.now().minusWeeks(1);
        List<Appointment> deleteList = appointmentRepository.findAllBeforeDateTime(oneWeek);
        for (Appointment appointment : deleteList) {
            appointmentRepository.delete(appointment);
        }
    }

    // saate bir aktif olan müşteri hesaplarının aktifliğini false yapma
    @Scheduled(cron = "0 0 * * * *")
    public void notActive() {
        for (Customer customer : customerRepository.findByActiveTrue()) {
            customer.setActive(false);
            customerRepository.save(customer);
        }
    }

    // giriş yapma süresi 5 aydan fazla olan hesapların cihazlardaki giriş cookisi silinmesi
    @Scheduled(cron = "0 0 0 1 * ?")
    public void logout() {
        LocalDateTime now = LocalDateTime.now();
        for (Token token : tokenRepository.findAll()) {
            Duration duration = Duration.between(token.getShop().getLoginDateTime(),now);
            if(duration.toDays()>=150){
                tokenRepository.delete(token);
            }
        }
    }

}

