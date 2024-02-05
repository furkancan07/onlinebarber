package com.rf.onlinebarber.Component;

import com.rf.onlinebarber.Entity.Appointment;
import com.rf.onlinebarber.Repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
    // burada her gün saat gece 12 de spring bootun otomatik çalışıp 1 haftadan eski randevuları silme işlemi yapılacak
    private final AppointmentRepository appointmentRepository;
    @Scheduled(cron = "0 0 0 * * ?") // her gün gece 12
    public void deleteAppointment() {
        LocalDateTime oneWeek=LocalDateTime.now().minusWeeks(1);
        List<Appointment> deleteList=appointmentRepository.findAllBeforeDateTime(oneWeek);
        for (Appointment appointment : deleteList){
            appointmentRepository.delete(appointment);
        }
    }
}

