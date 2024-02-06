package com.rf.onlinebarber.Component;

import com.rf.onlinebarber.Entity.Appointment;
import com.rf.onlinebarber.Entity.Customer;
import com.rf.onlinebarber.Repository.AppointmentRepository;
import com.rf.onlinebarber.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;

    // burada her gün saat gece 12 de spring bootun otomatik çalışıp 1 haftadan eski randevuları silme işlemi yapılacak
    @Scheduled(cron = "0 0 0 * * ?") // her gün gece 12
    public void deleteAppointment() {
        LocalDateTime oneWeek=LocalDateTime.now().minusWeeks(1);
        List<Appointment> deleteList=appointmentRepository.findAllBeforeDateTime(oneWeek);
        for (Appointment appointment : deleteList){
            appointmentRepository.delete(appointment);
        }
    }
    // saate bir aktif olan hesapların aktifliğini false yapma
    @Scheduled(cron = "0 0 * * * *")
    public void notActive(){
        for(Customer customer : customerRepository.findByActiveTrue()){
            customer.setActive(false);
            customerRepository.save(customer);
        }
    }
}

