/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class PassengerController {

    public static Response createPassenger(String id, String firstname, String lastname, String birthDate, String countryPhoneCode, String phone, String country) {
        try {
            int countryPhoneCodeInt;
            long idLong, phoneLong;
            LocalDate birthDateLD;

            try {
                birthDateLD = LocalDate.parse(birthDate);
                if (birthDateLD.isAfter(LocalDate.now())) {
                    return new Response("Birth Date must be in the past", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Birth Date must be numeric", Status.BAD_REQUEST);
            }

            try {
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            try {
                phoneLong = Long.parseLong(id);
                if (phoneLong < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            try {
                countryPhoneCodeInt = Integer.parseInt(countryPhoneCode);
                if (countryPhoneCodeInt < 0) {
                    return new Response("Country Phone Code must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Country Phone Code must be numeric", Status.BAD_REQUEST);
            }

            try {
                phoneLong = Long.parseLong(phone);
                if (phoneLong < 0) {
                    return new Response("phone must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("phone must be numeric", Status.BAD_REQUEST);
            }

            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }

            if (country.equals("")) {
                return new Response("country must be not empty", Status.BAD_REQUEST);
            }

            
            
            return null;
        } catch (Exception ex) {
            return null;
        }

    }
    
    
}
