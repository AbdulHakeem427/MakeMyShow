package com.example.bookmyshowcb.controllers;

import com.example.bookmyshowcb.controllers.dto.CreateBookingRequestDTO;
import com.example.bookmyshowcb.controllers.dto.CreateBookingResponseDTO;
import com.example.bookmyshowcb.controllers.enums.ResponseStatus;
import com.example.bookmyshowcb.models.Booking;
import com.example.bookmyshowcb.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public CreateBookingResponseDTO createBooking(@RequestBody CreateBookingRequestDTO requestDTO) {
        CreateBookingResponseDTO responseDTO = new CreateBookingResponseDTO();
        try{
            Booking booking = bookingService.createBooking(requestDTO.getUserId(), requestDTO.getShowId(), requestDTO.getShowSeatIds());


            responseDTO.setBookingId(booking.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            responseDTO.setBookingId(null);
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

}
