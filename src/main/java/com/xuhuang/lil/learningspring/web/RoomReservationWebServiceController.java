package com.xuhuang.lil.learningspring.web;

import java.util.Date;
import java.util.List;

import com.xuhuang.lil.learningspring.business.domain.RoomReservation;
import com.xuhuang.lil.learningspring.business.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class RoomReservationWebServiceController {
    private final ReservationService reservationService;

    @Autowired
    public RoomReservationWebServiceController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<RoomReservation> getRoomReservations(@RequestParam(name="date", required=false) String dataString) {
        Date date = DateUtils.createDateFromDateString(dataString);
        return this.reservationService.getRoomReservationsForDate(date);
    }
}
