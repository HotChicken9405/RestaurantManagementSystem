package com.restaurant.table.repository;

import com.restaurant.table.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findByTable_TableId(String tableId);
    List<Reservation> findByStatus(Reservation.ReservationStatus status);
}
