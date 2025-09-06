package com.example.bookmyshowcb.services;

import com.example.bookmyshowcb.exceptions.SeatAlreadyBookedException;
import com.example.bookmyshowcb.exceptions.ShowNotFoundException;
import com.example.bookmyshowcb.exceptions.ShowSeatTypeNotFoundException;
import com.example.bookmyshowcb.exceptions.UserNotFoundException;
import com.example.bookmyshowcb.models.Booking;
import com.example.bookmyshowcb.models.Show;
import com.example.bookmyshowcb.models.ShowSeat;
import com.example.bookmyshowcb.models.User;
import com.example.bookmyshowcb.models.enums.BookingStatus;
import com.example.bookmyshowcb.models.enums.ShowSeatStatus;
import com.example.bookmyshowcb.repositories.BookingRepository;
import com.example.bookmyshowcb.repositories.ShowRepository;
import com.example.bookmyshowcb.repositories.ShowSeatRepository;
import com.example.bookmyshowcb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class BookingService {

    private final ShowSeatRepository showSeatRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    private final PriceCalculationService priceCalculationService;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, PriceCalculationService priceCalculationService, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculationService = priceCalculationService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE) // this is how one request will book the seats, while others wait
    public Booking createBooking(long userId, long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException, SeatAlreadyBookedException, ShowSeatTypeNotFoundException {
        // 1. get the user from userId
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with Id" +userId+ "does not exist");
        }
        User user = optionalUser.get();

        // 2. get the show from showId
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show with Id" +showId+ "does not exist");
        }
        Show show = optionalShow.get();

        // 3. get the showseat obj. from showSeatIds
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        if(showSeats.isEmpty()){
            throw new ShowNotFoundException("ShowSeat with Id"+showSeatIds+ "does not exist");
        }

        // right now it is 7:30 AM
        // if the seat was lockedAt 7:00 AM
        // if the time difference between, now - lockedAt > 10 mins. I can use it!
        // if the time difference between, now - lockedAt <= 10 mins.
        // this seat is locked for someone else, My booking cannot go further
        for(ShowSeat showSeat : showSeats){
            ShowSeatStatus status = showSeat.getStatus();
            if(status == ShowSeatStatus.BOOKED){
                throw new SeatAlreadyBookedException("Seat with this Id "+showSeat.getId()+" is already booked");
            }
            Date currentDate = new Date();
            Date lockedAt = showSeat.getLockedAt();
            Long timeDifference = currentDate.getTime() - lockedAt.getTime();
            if(status == ShowSeatStatus.BLOCKED && TimeUnit.MINUTES.toMinutes(timeDifference) < 10){
                throw new SeatAlreadyBookedException("Seat with this Id "+showSeat.getId()+" is already booked");
            }

        }

        // if I reach this point in code
        // that means these seats are meant for my booking

        for(ShowSeat showSeat : showSeats){
            showSeat.setLockedAt(new Date());
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
        }
        showSeatRepository.save(showSeats);

        int totalAmount = priceCalculationService.getTotalAmount(show, showSeats);

        // create a booking summary
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setShowSeat(showSeats);
        booking.setTotalAmount(totalAmount);
        booking.setBookedAt(new Date());
        booking.setPayments(new ArrayList<>());
        booking.setBookingStatus(BookingStatus.PENDING);

        Booking saveBooking = bookingRepository.save(booking);

        return saveBooking;
        // 4. check if chosen ShowSeat objects are available
        // 5. BLOCK these chosen seats if they were available
        // 6. BLOCK for 10 mins.
        // 7. update the showSeat objects in DB as BLOCKED, with current timestamp as lockedAt
        // 8. get the total amount
        // 9. create the booking object. fill all the attributes

    }
}
