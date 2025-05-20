/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.PassengerStorage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                birthDateLD = LocalDate.parse(birthDate, formatter);
                if (birthDateLD.isAfter(LocalDate.now())) {
                    return new Response("Birth Date must be in the past", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Birth Date must be numeric", Status.BAD_REQUEST);
            }

            try {
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Id must be 0 or more than 0", Status.BAD_REQUEST);
                }
                
                if (id.length() > 15){
                    return new Response("Id must have at most 15 digits", Status.BAD_REQUEST);
                }
                
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            try {
                phoneLong = Long.parseLong(id);
                
                if (phoneLong < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
                
                if (phone.length() > 11){
                    return new Response("Phone number must have at most 15 digits", Status.BAD_REQUEST);
                }
                
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            try {
                countryPhoneCodeInt = Integer.parseInt(countryPhoneCode);
                if (countryPhoneCodeInt < 0) {
                    return new Response("Country Phone Code must be positive", Status.BAD_REQUEST);
                }
                if (countryPhoneCode.length() > 3) {
                    return new Response("Id must have at most 3 digits", Status.BAD_REQUEST);
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

            PassengerStorage storage = PassengerStorage.getInstance();            
            if (!storage.addPassenger(new Passenger(idLong, firstname, lastname, birthDateLD, countryPhoneCodeInt, phoneLong, country))) {
                return new Response("A person with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Person created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }
    
    public static Response updatePassenger(String id, String firstname, String lastname, String birthDate, String countryPhoneCode, String phone, String country) {
        try {
            int countryPhoneCodeInt;
            long idLong, phoneLong;
            LocalDate birthDateLD;
            
            try {
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Id must be 0 or more than 0", Status.BAD_REQUEST);
                }
                
                if (id.length() > 15){
                    return new Response("Id must have at most 15 digits", Status.BAD_REQUEST);
                }
                
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            
            try {
                birthDateLD = LocalDate.parse(birthDate);
                if (birthDateLD.isAfter(LocalDate.now())) {
                    return new Response("Birth Date must be in the past", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Birth Date must be numeric", Status.BAD_REQUEST);
            }
            
            
            try {
                phoneLong = Long.parseLong(id);
                
                if (phoneLong < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
                
                if (phone.length() > 11){
                    return new Response("Phone number must have at most 15 digits", Status.BAD_REQUEST);
                }
                
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            
            try {
                countryPhoneCodeInt = Integer.parseInt(countryPhoneCode);
                if (countryPhoneCodeInt < 0) {
                    return new Response("Country Phone Code must be positive", Status.BAD_REQUEST);
                }
                if (countryPhoneCode.length() > 3) {
                    return new Response("Id must have at most 3 digits", Status.BAD_REQUEST);
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
            
            
            PassengerStorage storage = PassengerStorage.getInstance();
            
            Passenger passenger = storage.getPassenger(idLong);
            if (passenger == null) {
                return new Response("Person not found", Status.NOT_FOUND);
            }
            
            
            passenger.setFirstname(firstname);
            passenger.setLastname(lastname);
            passenger.setBirthDate(birthDateLD);
            passenger.setCountry(country);
            passenger.setPhone(phoneLong);
            passenger.setCountryPhoneCode(countryPhoneCodeInt);
           
            return new Response("Passenger data updated successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
