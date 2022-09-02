package com.blockchain.data.anonymization.controller;

import com.blockchain.data.anonymization.Repository.CovidUserRepository;
import com.blockchain.data.anonymization.Repository.UserRepository;
import com.blockchain.data.anonymization.model.CovidUser;
import com.blockchain.data.anonymization.model.User;
import com.blockchain.data.anonymization.service.CovidUserService;
import com.blockchain.data.anonymization.service.CustomUserDetails;
import com.blockchain.data.anonymization.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Controller
public class CovidUserController {

    @Autowired
    private CovidUserService covidUserService;


    @GetMapping("/addCovidCase")
    public String showCovidRegistrationForm(Model model) {
        model.addAttribute("covidUser", new CovidUser());
        return "covid_register_form";
    }

    @PostMapping("/addCovidCase")
    public String processCovidUser(CovidUser user) {
        covidUserService.createCovidCase(user);

        return "covid_user_success";
    }

    @GetMapping("/retriveCovidCases")
    public int retrieveCovidCasesInArea(@RequestParam String area){

        return covidUserService.getCovidCasesInArea(area);
    }

    @GetMapping("/covidUsersAround")
    public String listUsers(Model model) {
        String streetArea = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            streetArea = ((CustomUserDetails) principal).getUser().getStreetArea();
        }
        int covidCasesInArea = covidUserService.getCovidCasesInArea(streetArea);
        model.addAttribute("covidCasesInArea", covidCasesInArea);

        return "covid_count";
    }
}
