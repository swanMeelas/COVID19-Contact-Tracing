package com.blockchain.data.anonymization.model;

import com.blockchain.data.anonymization.dto.Block;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Base64;

@Data
@Entity
@Table(name = "covid_user")
@AllArgsConstructor
@NoArgsConstructor
public class CovidUser {
    public static final int prefix = 4;
    public static final String prefixString = new String(new char[prefix]).replace('\0', '0');

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "street_no")
    private String streetNo;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_area")
    private String streetArea;

    @Column(name = "phone")
    private String phone;

    @Column(name = "covid_start_date")
    private Date covidStartDate;

    public void setFirstName(String firstName) {
        this.firstName = anonymizeCovidData(firstName);
    }
    public void setLastName(String lastName) {
        this.lastName =  anonymizeCovidData(lastName);
    }
    public void setStreetName(String streetName) {
        this.streetName =  anonymizeCovidData(streetName);
    }
    public void setPhone(String phone) {
        this.phone = anonymizeCovidData(phone);
    }


    public void setStreetArea(String streetArea) {
        this.streetArea = anonymizeCovidData(streetArea);
    }



    private String anonymizeCovidData(String input) {
        String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
        Block newBlock = new Block(
                encodedString,
                prefixString,
                new java.util.Date().getTime());
        return newBlock.mineBlock(prefix);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetArea() {
        return deMineBlock(streetArea);
    }

    public String getPhone() {
        return phone;
    }

    public String deMineBlock(String data) {
        if(data != null){
            String[] split = data.split(":");
            byte[] decodedBytes = Base64.getDecoder().decode( split[1]);
            String decodedString = new String(decodedBytes);
            return decodedString;
        }
       return "";
    }
}
