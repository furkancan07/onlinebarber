package com.rf.onlinebarber.Dto;

import com.rf.onlinebarber.Entity.Appointment;
import com.rf.onlinebarber.Entity.ShavingModel;
import com.rf.onlinebarber.Entity.Shop;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {
    public ShopResponse shopConverter(Shop shop) {
        return ShopResponse.builder().name(shop.getName()).id(shop.getId()).image(shop.getImage()).build();
    }

    public ShavingModelResponse modelConverter(ShavingModel model) {
        return ShavingModelResponse.builder().name(model.getName()).price(model.getPrice()).id(model.getId())
                .image(model.getImage()).shop((shopConverter(model.getShop())))
                .build();
    }

    public AppointmentResponse appointmentConverter(Appointment appointment) {
        return AppointmentResponse.builder().
                shavingModel(modelConverter(appointment.getShavingModel())).date(appointment.getDate())
                .customer(appointment.getCustomer()).id(appointment.getId())
                .build();
    }
}
