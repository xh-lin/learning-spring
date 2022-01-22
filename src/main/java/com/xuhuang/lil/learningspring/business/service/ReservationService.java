package com.xuhuang.lil.learningspring.business.service;

import java.util.Date;
import java.util.List;

import com.xuhuang.lil.learningspring.business.domain.RoomReservation;
import com.xuhuang.lil.learningspring.data.repository.GuestRepository;
import com.xuhuang.lil.learningspring.data.repository.ReservationRepository;
import com.xuhuang.lil.learningspring.data.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    
    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        return null;
    }
}
