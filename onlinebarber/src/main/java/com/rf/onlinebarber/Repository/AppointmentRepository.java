package com.rf.onlinebarber.Repository;

import com.rf.onlinebarber.Entity.Appointment;
import com.rf.onlinebarber.Entity.Customer;
import com.rf.onlinebarber.Entity.ShavingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    public List<Appointment> findByCustomerAndDate(Customer customer, LocalDateTime dateTime);
    public List<Appointment> findByCustomerId(Long customerId);
    @Query("SELECT a FROM Appointment a WHERE a.date < :dateTime")
    List<Appointment> findAllBeforeDateTime(@Param("dateTime") LocalDateTime dateTime);


}
