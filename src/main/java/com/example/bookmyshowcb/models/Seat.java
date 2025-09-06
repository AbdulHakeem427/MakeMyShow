package com.example.bookmyshowcb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "seats")
public class Seat extends BaseModel{
    private int seatNumber;

    @ManyToOne
    private SeatType seatType;

    @Column(name = "row_num")
    private int rowNumber;

    @Column(name = "col_num")
    private int columnNumber;
}
