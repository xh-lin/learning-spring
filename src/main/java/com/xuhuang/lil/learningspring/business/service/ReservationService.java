package com.xuhuang.lil.learningspring.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xuhuang.lil.learningspring.business.domain.GuestInfo;
import com.xuhuang.lil.learningspring.business.domain.RoomReservation;
import com.xuhuang.lil.learningspring.data.entity.Guest;
import com.xuhuang.lil.learningspring.data.entity.Reservation;
import com.xuhuang.lil.learningspring.data.entity.Room;
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
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(), roomReservation);
        });

        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });

        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Map.Entry<Long, RoomReservation> entry: roomReservationMap.entrySet()) {
            roomReservations.add(entry.getValue());
        }

        roomReservations.sort((o1, o2) -> o1.getRoomName().equals(o2.getRoomName())
            ? o1.getRoomNumber().compareTo(o2.getRoomNumber())
            : o1.getRoomName().compareTo(o2.getRoomName()));
        
        return roomReservations;
    }

    public List<GuestInfo> getGuestInfos() {
        Iterable<Guest> guests = this.guestRepository.findAll();
        List<GuestInfo> guestInfos = new ArrayList<>();

        guests.forEach(guest -> {
            GuestInfo guestInfo = new GuestInfo();
            guestInfo.setFirstName(guest.getFirstName());
            guestInfo.setLastName(guest.getLastName());
            guestInfo.setEmailAddress(guest.getEmailAddress());
            guestInfo.setPhoneNumber(guest.getPhoneNumber());
            guestInfos.add(guestInfo);
        });

        guestInfos.sort((o1, o2) -> o1.getLastName().equals(o2.getLastName())
            ? o1.getFirstName().compareTo(o2.getFirstName())
            : o1.getLastName().compareTo(o2.getLastName()));

        return guestInfos;
    }
}
