package com.example.bookmyshowcb.controllers.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDTO {
    public Long userId;
    public Long showId;
    public List<Long> showSeatIds;
}
