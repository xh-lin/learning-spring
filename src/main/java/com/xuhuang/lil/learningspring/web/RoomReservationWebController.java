package com.xuhuang.lil.learningspring.web;

import java.util.Date;
import java.util.List;

import com.xuhuang.lil.learningspring.business.domain.RoomReservation;
import com.xuhuang.lil.learningspring.business.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservations")
public class RoomReservationWebController {
    private final ReservationService reservationService;

    @Autowired
    public RoomReservationWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // @GetMapping: at the requesting URL, any GET method will be responded by this method
    @GetMapping
    public String getReservations(@RequestParam(value="date", required=false) String dateString, Model model) {
        Date date = DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);
        model.addAttribute("roomReservations", roomReservations);
        // it tells Thymeleaf to pass model to a template named 'reservations', then merge with Thymeleaf engine
        return "reservations";
    }
}
