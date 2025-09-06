package com.example.bookmyshowcb.controllers.dto;

import com.example.bookmyshowcb.controllers.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingResponseDTO {
    public Long bookingId;
    public ResponseStatus responseStatus;
}
