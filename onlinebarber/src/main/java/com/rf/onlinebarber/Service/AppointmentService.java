package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Configuration.BeanConfig;
import com.rf.onlinebarber.Dto.AppointmentResponse;
import com.rf.onlinebarber.Dto.CreateAppointmentRequest;
import com.rf.onlinebarber.Dto.DtoConverter;
import com.rf.onlinebarber.Entity.Appointment;
import com.rf.onlinebarber.Entity.Customer;
import com.rf.onlinebarber.Entity.ShavingModel;
import com.rf.onlinebarber.Entity.Shop;
import com.rf.onlinebarber.Exception.AuthorizationException;
import com.rf.onlinebarber.Exception.CustomerActivationException;
import com.rf.onlinebarber.Exception.ModelNotFoundException;
import com.rf.onlinebarber.Repository.AppointmentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CustomerService customerService;
    private final ShavingModelService shavingModelService;
    private final ShopService shopService;
    private final DtoConverter converter;
    private final MailService mailService;
    private final BeanConfig config;

    private List<Appointment> appointments;

    @Transactional(rollbackOn = MailException.class)
    public ResponseEntity<?> createAppointment(Long customerId, Long modelId, CreateAppointmentRequest request) {
        Customer customer = customerService.findById(customerId);
        ShavingModel model = shavingModelService.findById(modelId);
        Appointment appointment = new Appointment();
        if (!isAvailable(request.getDateTime(), model.getShop().getId())) {
            return ResponseEntity.badRequest().body("Berber musait değil lütgen başka bir tarih için tekrar randevu alın");
        }
        if (!anotherAppointmentValid(request.getDateTime(), customer)) {
            return ResponseEntity.badRequest().body("Bugün başka bir yerde daha randevunuz gözüküyor lütfen iptal edip tekrLr deneyin");
        }
        appointment.setCustomer(customer);
        appointment.setShavingModel(model);
        appointment.setDate(request.getDateTime());
        appointmentRepository.save(appointment);
        mailService.sendAppointmentİnformation(appointment);
        return ResponseEntity.ok("Sayın+ " + customer.getEmail() + " " + request.getDateTime() + " tarihinde randevunuz oluşmuştur");

    }

    // bir mağazaya ait randevular
    public List<AppointmentResponse> appointmentList(Long shopId) {
        if(!config.isAuthenticate(config.getAuthentication(),shopService.findById(shopId))) throw new AuthorizationException();
        List<Appointment> appointmentS = new ArrayList<>();
        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getShavingModel().getShop().getId() == shopId) {
                appointmentS.add(appointment);
            }
        }
        return appointmentS.stream().map(converter::appointmentConverter).collect(Collectors.toList());
    }

    // berber müsait mi kontrolu
    private boolean isAvailable(LocalDateTime dateTime, Long shopId) {
        // seçilen tarih şu andan eski ,günlerden pazar ve saat 9 ile 22 arasinda değilse randevu oluşturmayacak
        if (dateTime.isBefore(LocalDateTime.now()) || dateTime.getDayOfWeek() == DayOfWeek.SUNDAY || dateTime.getHour() < 9 || dateTime.getHour() >= 22) {
            return false;
        }
        for (AppointmentResponse appointment : appointmentList(shopId)) {
            if (appointment.getDate().isEqual(dateTime)) {
                System.out.println("tarihler ayni");
                return false;
            }
        }
        System.out.println("asasa");

        return true;
    }

    // müşteri aynı gün içinde başka bir berbere randevu oluşturmuş mu
    private boolean anotherAppointmentValid(LocalDateTime dateTime, Customer customer) {
        for (Appointment appointment : appointmentRepository.findByCustomerAndDate(customer, dateTime)) {
            if (isSameDay(appointment.getDate(), dateTime)) {
                System.out.println("hata");
                return false;
            }
        }
        System.out.println("çalışır");
        return true;
    }

    private boolean isSameDay(LocalDateTime date1, LocalDateTime date2) {
        return date1.getDayOfYear() == date2.getDayOfYear() && date1.getYear() == date2.getYear();
    }
// randevu iptal edilince müşteriye mail gönderilsin
    @Transactional(rollbackOn = MailException.class)
    public ResponseEntity<?> cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new ModelNotFoundException(id));
        mailService.sendCancelAppointment(appointment);
        appointmentRepository.delete(appointment);
        return ResponseEntity.ok("randevu iptal edildi");
    }

    public ResponseEntity<?> getCustomerAppointments(Long customerId) {
        Customer customer=customerService.findById(customerId);
        if(!customer.isActive())  throw new CustomerActivationException();
        List<AppointmentResponse> list=appointmentRepository.findByCustomerId(customerId).stream().map(converter::appointmentConverter).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
