package com.blockchain.data.anonymization.service;

import com.blockchain.data.anonymization.Repository.CovidUserRepository;
import com.blockchain.data.anonymization.model.CovidUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class CovidUserService {
    @Autowired
    CovidUserRepository covidUserRepository;

    public String createCovidCase(CovidUser covidUser) {
        if (covidUser.getCovidStartDate() == null) {
            covidUser.setCovidStartDate(new java.sql.Date(new java.util.Date().getTime()));
        }
        CovidUser save = covidUserRepository.save(covidUser);
        return "Success";
    }

    public int getCovidCasesInArea(String area) {

        String encodedString = Base64.getEncoder().encodeToString(area.getBytes());
        LocalDate currentDate = LocalDate.now();
        List<CovidUser> allByStreetAreas = covidUserRepository.findAllByStreetAreas(encodedString);
        int count = 0;
        for (CovidUser c : allByStreetAreas) {
            LocalDate localDate = c.getCovidStartDate().toLocalDate();
            long dateDifference = DAYS.between(localDate, currentDate);
            if (dateDifference <= 14) {
                count++;
            }
        }
        return count;
    }

    public Integer getCovidCasesInMyArea(String area) {
        String encodedString = Base64.getEncoder().encodeToString(area.getBytes());
        LocalDate currentDate = LocalDate.now();
        List<CovidUser> allByStreetAreas = covidUserRepository.findAllByStreetAreas(encodedString);
        int count = 0;
        for (CovidUser c : allByStreetAreas) {
            LocalDate localDate = c.getCovidStartDate().toLocalDate();
            long dateDifference = DAYS.between(localDate, currentDate);
            if (dateDifference <= 14) {
                count++;
            }
        }
        return count;
    }
}
