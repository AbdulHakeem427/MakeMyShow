package com.example.bookmyshowcb.services;

import com.example.bookmyshowcb.exceptions.ShowSeatTypeNotFoundException;
import com.example.bookmyshowcb.models.*;

import com.example.bookmyshowcb.repositories.ShowSeatRepository;
import com.example.bookmyshowcb.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceCalculationService {

    private final ShowSeatRepository showSeatRepository;
    private final ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculationService(ShowSeatRepository showSeatRepository, ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatRepository = showSeatRepository;
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int getTotalAmount(Show show, List<ShowSeat> showSeats) throws ShowSeatTypeNotFoundException {
        // 1. get the corresponding SeatTypes
        int totalAmount = 0;
        for(ShowSeat showSeat : showSeats){
            Seat seat = showSeat.getSeat();
            SeatType seatType = seat.getSeatType();

            Optional<ShowSeatType> optionalShowSeatType = showSeatTypeRepository.findShowSeatTypeByShowAndSeatType(show, seatType);
            if(optionalShowSeatType.isEmpty()){
                throw new ShowSeatTypeNotFoundException("Show seat type doesnot exist");
            }
            ShowSeatType showSeatType = optionalShowSeatType.get();
            totalAmount += showSeatType.getPrice();
        }
        totalAmount += applyExtraCostForLateNightShow(show, totalAmount);
        totalAmount += addChargesForBookingUrgently(show, totalAmount);
        return totalAmount;
    }

    private int applyExtraCostForLateNightShow(Show show, int basePrice) {
        return 0;
    }
    private int addChargesForBookingUrgently(Show show, int basePrice) {
        return 0;
    }
}
