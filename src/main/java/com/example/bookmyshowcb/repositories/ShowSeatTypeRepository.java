package com.example.bookmyshowcb.repositories;

import com.example.bookmyshowcb.models.SeatType;
import com.example.bookmyshowcb.models.Show;
import com.example.bookmyshowcb.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    Optional<ShowSeatType> findShowSeatTypeByShowAndSeatType(Show show, SeatType seatType);
}
