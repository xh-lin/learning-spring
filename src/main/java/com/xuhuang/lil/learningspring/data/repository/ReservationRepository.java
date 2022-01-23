package com.xuhuang.lil.learningspring.data.repository;

import java.sql.Date;

import com.xuhuang.lil.learningspring.data.entity.Reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    // Spring Data will build the query for us based on the name of the method.
    Iterable<Reservation> findReservationByReservationDate(Date date);
}
