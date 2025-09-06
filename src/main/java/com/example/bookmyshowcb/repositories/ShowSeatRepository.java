package com.example.bookmyshowcb.repositories;

import com.example.bookmyshowcb.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    List<ShowSeat> findAllById(Iterable<Long> showSeatIDS);

    List<ShowSeat> save(List<ShowSeat> showSeats);
}
