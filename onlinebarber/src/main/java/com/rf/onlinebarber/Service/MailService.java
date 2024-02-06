package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Entity.Appointment;
import com.rf.onlinebarber.Entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender sender;

    // müşteriye aktivasyon kodu gönder 6 hanelik
    public void sendActivationCode(Customer customer) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@myonlinebarber-app.com");
        mailMessage.setTo(customer.getEmail());
        mailMessage.setSubject("Aktivasyon Kodu Doğrulama");
        mailMessage.setText(customer.getActivationCode());
        sender.send(mailMessage);
    }

    // müşteriye siparişiniz oluşmuştur bilgisini gönderme
    public void sendAppointmentİnformation(Appointment appointment) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@myonlinebarber-app.com");
        mailMessage.setTo(appointment.getCustomer().getEmail());
        mailMessage.setSubject("Berber Randevusu");
        mailMessage.setText("Sayın " + appointment.getCustomer().getEmail() +
                 appointment.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) + " tarihindeki randevunuz  oluşmuştur");
        sender.send(mailMessage);
    }

    // müşteriye sipariş iptal oldu bilgisini gönderme
    public void sendCancelAppointment(Appointment appointment) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@myonlinebarber-app.com");
        mailMessage.setTo(appointment.getCustomer().getEmail());
        mailMessage.setSubject("Randevu İptal");
        mailMessage.setText("Sayın " + appointment.getCustomer().getEmail() +
                 appointment.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " tarihindeki randevunuz iptal edilmiştir");
        sender.send(mailMessage);
    }

}
