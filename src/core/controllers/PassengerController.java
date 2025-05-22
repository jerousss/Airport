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
import java.time.format.DateTimeParseException;

/**
 *
 * @author USER
 */
public class PassengerController {

    public static Response createPassenger(String id, String firstname, String lastname, String year, String month, String day, String countryPhoneCode, String phone, String country) {
        try {
            int countryPhoneCodeInt;
            long idLong, phoneLong;
            int yearInt, monthInt, dayInt;

            try {
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Id must be 0 or more than 0", Status.BAD_REQUEST);
                }

                if (id.length() > 15) {
                    return new Response("Id must have at most 15 digits", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }

            try {
                yearInt = Integer.parseInt(year);
                monthInt = Integer.parseInt(month);
                dayInt = Integer.parseInt(day);
                if (yearInt > 2024) {
                    return new Response("Year must be 2024 or before", Status.BAD_REQUEST);
                }
                if (month.length() == 1) {
                    month = "0" + month;
                }
                if (day.length() == 1) {
                    day = "0" + day;
                }
            } catch (NumberFormatException ex) {
                return new Response("Birth Date must be numeric", Status.BAD_REQUEST);
            }

            String birthDateStr = year + "-" + month + "-" + day;
            LocalDate birthDate = LocalDate.parse(birthDateStr);

            if (countryPhoneCode.equals("")) {
                return new Response("Phone code must be not empty", Status.BAD_REQUEST);
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

            if (phone.equals("")) {
                return new Response("Phone number must be not empty", Status.BAD_REQUEST);
            }
            try {
                phoneLong = Long.parseLong(phone);

                if (phoneLong < 0) {
                    return new Response("phone number must be positive", Status.BAD_REQUEST);
                }

                if (phone.length() > 11) {
                    return new Response("Phone number must have at most 15 digits", Status.BAD_REQUEST);
                }

                if (phoneLong < 0) {
                    return new Response("phone must be positive", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException ex) {
                return new Response("phone must be numeric", Status.BAD_REQUEST);
            }
            if (country.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            try {
                Double.parseDouble(country);
                return new Response("Country must be a String (not numeric)", Status.BAD_REQUEST);

            } catch (NumberFormatException ex) {

            }

            PassengerStorage storage = PassengerStorage.getInstance();

            if (!storage.addPassenger(new Passenger(idLong, firstname, lastname, birthDate, countryPhoneCodeInt, phoneLong, country))) {
                return new Response("A passenger with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Passenger created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }

    public static Response updatePassenger(String id, String firstname, String lastname, String year, String month, String day, String countryPhoneCode, String phone, String country) {
        try {
            int countryPhoneCodeInt;
            long idLong, phoneLong;
            int yearInt, monthInt, dayInt;

            try {
                if (id.equals("")) {
                    return new Response("Id must be not empty", Status.BAD_REQUEST);
                }
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Id must be 0 or more than 0", Status.BAD_REQUEST);
                }

                if (id.length() > 15) {
                    return new Response("Id must have at most 15 digits", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (firstname.equals("")) {
                return new Response("Firstname must be not empty ", Status.BAD_REQUEST);
            }

            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }

            try {
                yearInt = Integer.parseInt(year);
                monthInt = Integer.parseInt(month);
                dayInt = Integer.parseInt(day);
                if (yearInt > 2024) {
                    return new Response("Year must be 2024 or before", Status.BAD_REQUEST);
                }
                if (month.length() == 1) {
                    month = "0" + month;
                }
                if (day.length() == 1) {
                    day = "0" + day;
                }
            } catch (NumberFormatException ex) {
                return new Response("Birth Date must be numeric", Status.BAD_REQUEST);
            }
            
            try {

                if (day.length() == 1) {
                    day = "0" + day;
                }
            } catch (NumberFormatException ex) {
                return new Response("Day invalid", Status.BAD_REQUEST);
            }

            String birthDateStr = year + "-" + month + "-" + day;
            LocalDate birthDate = LocalDate.parse(birthDateStr);

            if (countryPhoneCode.equals("")) {
                return new Response("Phone code must be not empty", Status.BAD_REQUEST);
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

            if (phone.equals("")) {
                return new Response("Phone number must be not empty", Status.BAD_REQUEST);
            }
            try {
                phoneLong = Long.parseLong(phone);

                if (phoneLong < 0) {
                    return new Response("phone number must be positive", Status.BAD_REQUEST);
                }

                if (phone.length() > 11) {
                    return new Response("Phone number must have at most 15 digits", Status.BAD_REQUEST);
                }

                if (phoneLong < 0) {
                    return new Response("phone must be positive", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException ex) {
                return new Response("phone must be numeric", Status.BAD_REQUEST);
            }
            if (country.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            try {
                Double.parseDouble(country);
                return new Response("Country must be a String (not numeric)", Status.BAD_REQUEST);

            } catch (NumberFormatException ex) {

            }

            PassengerStorage storage = PassengerStorage.getInstance();

            Passenger passenger = storage.getPassenger(idLong);
            if (passenger == null) {
                return new Response("Person not found", Status.NOT_FOUND);
            }

            passenger.setFirstname(firstname);
            passenger.setLastname(lastname);
            passenger.setBirthDate(birthDate);
            passenger.setCountry(country);
            passenger.setPhone(phoneLong);
            passenger.setCountryPhoneCode(countryPhoneCodeInt);

            return new Response("Passenger data updated successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
