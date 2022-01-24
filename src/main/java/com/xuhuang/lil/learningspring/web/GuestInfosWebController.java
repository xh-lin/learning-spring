package com.xuhuang.lil.learningspring.web;

import java.util.List;

import com.xuhuang.lil.learningspring.business.domain.GuestInfo;
import com.xuhuang.lil.learningspring.business.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guests")
public class GuestInfosWebController {
    private final ReservationService reservationService;

    @Autowired
    public GuestInfosWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getGuests(Model model) {
        List<GuestInfo> guestInfos = this.reservationService.getGuestInfos();
        model.addAttribute("guestInfos", guestInfos);
        return "guests";
    }
}
