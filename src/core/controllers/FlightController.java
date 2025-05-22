/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PlaneStorage;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author GERONIMO
 */
public class FlightController {

    public static Response createFlight(String id, String plane, String departureLocation, String arrivalLocation, String departureDate, String hoursDurationArrival, String minutesDurationArrival) {

        try {
            int intHoursDurationArrival, intMinutesDurationArrival;
            LocalDate departureDateLD;

            try {
                departureDateLD = LocalDate.parse(departureDate);
                if (departureDateLD.isBefore(LocalDate.now()) || departureDateLD.isEqual(LocalDate.now())) {
                    return new Response("Departure Date must be in the future or today", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Departure Date must be numeric", Status.BAD_REQUEST);
            }

            try {
                intHoursDurationArrival = Integer.parseInt(hoursDurationArrival);
                if (intHoursDurationArrival < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            try {
                intMinutesDurationArrival = Integer.parseInt(minutesDurationArrival);
                if (intMinutesDurationArrival < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (id.equals("")) {
                return new Response("id must be not empty", Status.BAD_REQUEST);
            }

            if (id.length() < 6 || id.length() > 6) {
                return new Response("id must be exactly 6 character long", Status.BAD_REQUEST);
            }

            if (!Character.isUpperCase(id.charAt(0)) || !Character.isUpperCase(id.charAt(1)) || !Character.isUpperCase(id.charAt(2))) {
                return new Response("id must start with three uppercase letters", Status.BAD_REQUEST);
            }

            for (int i = 3; i < 6; i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    return new Response("id must end with 3 digits", Status.BAD_REQUEST);
                }
            }
           
            FlightStorage storage = FlightStorage.getInstance();
            if (!storage.addFlight(new Flight(id, plane, departureLocation, arrivalLocation, departureDateLD, intHoursDurationArrival, intMinutesDurationArrival))) {
                return new Response("A Flight with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Flight created successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }

    public static Response createFlight(String id, String plane, String departureLocation, String scaleLocation, String arrivalLocation, String departureDate, String hoursDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {

        try {

            int intHoursDurationArrival, intMinutesDurationArrival, inthoursDurationScale, intMinutesDurationScale;
            LocalDate departureDateLD;

            try {
                departureDateLD = LocalDate.parse(departureDate);
                if (departureDateLD.isBefore(LocalDate.now()) || departureDateLD.isEqual(LocalDate.now())) {
                    return new Response("Departure Date must be in the future or today", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Departure Date must be numeric", Status.BAD_REQUEST);
            }

            try {
                intHoursDurationArrival = Integer.parseInt(hoursDurationArrival);
                if (intHoursDurationArrival < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            try {
                intMinutesDurationScale = Integer.parseInt(minutesDurationScale);
                if (intMinutesDurationScale < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            try {
                inthoursDurationScale = Integer.parseInt(hoursDurationScale);
                if (inthoursDurationScale < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (id.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            try {
                intMinutesDurationArrival = Integer.parseInt(minutesDurationArrival);
                if (intMinutesDurationArrival < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            Plane planep = PlaneStorage.getInstance().getPlane(id);
            if (plane == null) {
                return new Response("Plane not found with the given ID", Status.BAD_REQUEST);
            }
            FlightStorage storage = FlightStorage.getInstance();
            if (!storage.addFlight(new Flight(id, planep, departureLocation, arrivalLocation, departureDateLD, intHoursDurationArrival, intMinutesDurationArrival, inthoursDurationScale, intMinutesDurationScale))) {
                return new Response("A Flight with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Flight created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
