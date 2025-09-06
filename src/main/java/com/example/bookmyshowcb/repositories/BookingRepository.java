package com.example.bookmyshowcb.repositories;

import com.example.bookmyshowcb.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {  //JpaRepository<Table, primary key>
    Booking save(Booking booking);
}
