package com.rf.onlinebarber.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "modelId")
    private ShavingModel shavingModel;
    private LocalDateTime date;
}
