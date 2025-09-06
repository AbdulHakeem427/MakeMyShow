package com.example.bookmyshowcb.models;

import com.example.bookmyshowcb.models.enums.ShowSeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

// contains all the attributes for the combination of
// show and a seat.
// mapping table

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show; // one ShowSeat has one Show(this is already we can see)...

    @ManyToOne
    private Seat seat; // one ShowSeat has one Seat(this is already we can see)...

    @Enumerated
    private ShowSeatStatus status;

    private Date lockedAt; // this will help me in maintaining the blocking status
    // and changing it back to available if needed
}
