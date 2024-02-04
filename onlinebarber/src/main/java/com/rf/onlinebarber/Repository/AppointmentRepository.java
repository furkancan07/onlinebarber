package com.rf.onlinebarber.Repository;

import com.rf.onlinebarber.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
