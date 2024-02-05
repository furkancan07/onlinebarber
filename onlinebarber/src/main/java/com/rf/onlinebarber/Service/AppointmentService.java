package com.rf.onlinebarber.Service;

import com.rf.onlinebarber.Dto.CreateAppointmentRequest;
import com.rf.onlinebarber.Entity.Appointment;
import com.rf.onlinebarber.Entity.Customer;
import com.rf.onlinebarber.Entity.ShavingModel;
import com.rf.onlinebarber.Repository.AppointmentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CustomerService customerService;
    private final ShavingModelService shavingModelService;

    private List<Appointment> appointments;

    @PostConstruct
    private void initAppointments() {
        this.appointments = appointmentRepository.findAll();
    }


    public ResponseEntity<?> createAppointment(Long customerId, Long modelId, CreateAppointmentRequest request) {
        Customer customer = customerService.findById(customerId);
        ShavingModel model=shavingModelService.findById(modelId);
        Appointment appointment=new Appointment();
        if(isAvailable(request.getDateTime(),model.getShop().getId())){
            appointment.setCustomer(customer);
            appointment.setShavingModel(model);
            appointment.setDate(request.getDateTime());
            appointmentRepository.save(appointment);
            return ResponseEntity.ok("Sayın+ "+customer.getEmail()+" "+ request.getDateTime()+" tarihinde randevunuz oluşmuştur");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Berber bu tarihte musait değil lütfen tekrar deneyiniz");
    }
    // bir mağazaya ait randevular
    public List<Appointment> appointmentList(Long shopId){
        List<Appointment> appointmentS=new ArrayList<>();
        for(Appointment appointment : appointments){
            if(appointment.getShavingModel().getShop().getId()==shopId){
                appointmentS.add(appointment);
            }
        }
        return appointmentS;
    }

    // berber müsait mi kontrolu
    private boolean isAvailable(LocalDateTime dateTime, Long shopId) {
        // seçilen tarih şu andan eski ,günlerden pazar ve saat 9 ile 22 arasinda değilse randevu oluşturmayacak
        if(dateTime.isBefore(LocalDateTime.now()) || dateTime.getDayOfWeek()== DayOfWeek.SUNDAY || dateTime.getHour()<9 || dateTime.getHour()>=22){
            return false;
        }
        for(Appointment appointment : appointmentList(shopId)){
            if(appointment.getDate().isEqual(dateTime)){
                return false;
            }
        }
        return true;
    }
}
