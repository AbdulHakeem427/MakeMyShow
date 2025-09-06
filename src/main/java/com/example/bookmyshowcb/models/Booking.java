package com.example.bookmyshowcb.models;

import com.example.bookmyshowcb.models.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;
    private Date bookedAt;
    private int totalAmount;

    @OneToMany
    private List<ShowSeat> showSeat;

    @OneToMany
    private List<Payment> payments;

    @Enumerated
    private BookingStatus bookingStatus;
}
