/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import core.models.SOLID.CalcArrivalDateFlight;
import core.models.SOLID.DelayFlights;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PassengerStorage;
import core.models.storage.PlaneStorage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GERONIMO
 */
public class FlightController {

    public static Response createFlight(String id, String planeID, String departureLocationID, String scaleLocationID, String arrivalLocationID, String year, String month, String day, String hour, String minutes, String hoursDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale) {
        try {
            if (id == null || id.isEmpty()) {
                return new Response("Id must be not empty", Status.BAD_REQUEST);
            }
            if (!isValidFlightIdFormat(id)) {
                return new Response("Id format invalid, must be 3 Upercase letters and 3 numbers", Status.BAD_REQUEST);
            }

            try {

            } catch (Exception e) {
                if (planeID == null || planeID.isEmpty()) {
                    return new Response("Plane must be selected", Status.BAD_REQUEST);
                }
            }

            if (departureLocationID == null || departureLocationID.isEmpty()) {
                return new Response("Departure location must be selected", Status.BAD_REQUEST);
            }

            if (arrivalLocationID == null || arrivalLocationID.isEmpty()) {
                return new Response("Arrival location must be selected", Status.BAD_REQUEST);
            }

            if (scaleLocationID == null || scaleLocationID.isEmpty()) {
                return new Response("Arrival location must be selected", Status.BAD_REQUEST);
            }

            int yearInt;
            int monthInt;
            int dayInt;
            int hourInt;
            int minutesInt;

            try {

                yearInt = Integer.parseInt(year);
                if (yearInt < LocalDateTime.now().getYear() - 1) {
                    return new Response("Year must be greater than " + (LocalDateTime.now().getYear() - 1), Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (year.equals("")) {
                    return new Response("Year departure must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Year departure must be just numeric", Status.BAD_REQUEST);
            }

            try {
                monthInt = Integer.parseInt(month);
                if (monthInt > 12) {
                    return new Response("Month departure invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Month departure must be selected", Status.BAD_REQUEST);
            }

            try {
                dayInt = Integer.parseInt(day);
                if (dayInt > 31) {
                    return new Response("Day departure invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Day departure must be selected", Status.BAD_REQUEST);
            }

            try {
                hourInt = Integer.parseInt(hour);
                if (hourInt < 0) {
                    return new Response("Hour departure must be greater than 0", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Hour departure must be selected", Status.BAD_REQUEST);
            }

            try {
                minutesInt = Integer.parseInt(minutes);
                if (minutesInt < 0 || minutesInt > 59) {
                    return new Response("Minutes departure must be greater than 0", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Minutes departure must be selected", Status.BAD_REQUEST);
            }

            int hourArrival;
            try {
                hourArrival = Integer.parseInt(hoursDurationArrival);
                if (hourArrival < 0 || hourArrival > 23) {
                    return new Response("Hours of arrival duration must be a non-negative integer", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Hours of arrival duration must be selected", Status.BAD_REQUEST);
            }

            int minuteArrival;
            try {
                minuteArrival = Integer.parseInt(minutesDurationArrival);
                if (minuteArrival < 0 || minuteArrival > 59) {
                    return new Response("Minutes of arrival duration must be between 0 and 59", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Minutes of arrival duration must be selected", Status.BAD_REQUEST);
            }

            if (hoursDurationScale == null || hoursDurationScale.isEmpty()) {
                return new Response("Hours of scale duration must be not empty", Status.BAD_REQUEST);
            }
            int hourScale;
            try {
                hourScale = Integer.parseInt(hoursDurationScale);
                if (hourScale < 0) {
                    return new Response("Hours of scale duration must be a non-negative integer", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Hours of scale duration must be selected", Status.BAD_REQUEST);
            }

            if (minutesDurationScale == null || minutesDurationScale.isEmpty()) {
                return new Response("Minutes of scale duration must be not empty", Status.BAD_REQUEST);
            }
            int minuteScale;
            try {
                minuteScale = Integer.parseInt(minutesDurationScale);
                if (minuteScale < 0 || minuteScale > 59) {
                    return new Response("Minutes of scale duration must be between 0 and 59", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Minutes of scale duration must be selected", Status.BAD_REQUEST);
            }

            LocalDateTime departureDate = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minutesInt);

            LocationStorage Lstorage = LocationStorage.getInstance();

            Location departureLocation = Lstorage.getLocation(departureLocationID);
            Location scaleLocation = Lstorage.getLocation(scaleLocationID);
            Location arrivalLocation = Lstorage.getLocation(arrivalLocationID);

            PlaneStorage Pstorage = PlaneStorage.getInstance();
            Plane plane = Pstorage.getPlane(planeID);

            FlightStorage storageFlight = FlightStorage.getInstance();
            Flight flight = storageFlight.getFlight(id);

            if (flight != null) {
                return new Response("Flight with this ID already exists", Status.BAD_REQUEST);
            }

            if (scaleLocation != null && hourScale==0 && minuteScale==0) {
                if (!storageFlight.addFlight(new Flight(id, plane, departureLocation, arrivalLocation, departureDate, hourArrival, minuteArrival))) {
                    return new Response("This flight already exits", Status.BAD_REQUEST);
                }
                return new Response("Flight created successfully", Status.CREATED);
            } else {
                if (!storageFlight.addFlight(new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hourArrival, minuteArrival, hourScale, minuteScale))) {
                    return new Response("This flight already exits", Status.BAD_REQUEST);
                }
                return new Response("Flight with scale created successfully", Status.CREATED);
            }
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    private static boolean isValidFlightIdFormat(String id) {
        return id.matches("^[A-Z]{3}\\d{3}$");
    }

    public static void setFlightIdCombo(JComboBox<String> comboBox) {
        FlightStorage storage = FlightStorage.getInstance();
        for (Flight flight : storage.getFlights()) {
            comboBox.addItem(String.valueOf(flight.getId()));
        }
    }

    public static Response delayFlight(String FlightId, String hours, String minutes) {
        try {
            int hourint;
            int minint;

            try {
                hourint = Integer.parseInt(hours);
                minint = Integer.parseInt(minutes);
                if (hours.length() == 1) {
                    hours = "0" + hours;
                }
                if (minutes.length() == 1) {
                    minutes = "0" + minutes;
                }
                if (hourint == 0) {
                    return new Response("hour can´t be 00", Status.BAD_REQUEST);
                }
            } catch (Exception ex) {
                return new Response("Error", Status.INTERNAL_SERVER_ERROR);
            }

            FlightStorage storage = FlightStorage.getInstance();
            Flight flight = storage.getFlight(FlightId);
            if (flight == null) {
                return new Response("Flight not founded", Status.NOT_FOUND);
            }
            DelayFlights.delay(storage.getFlight(FlightId), hourint, minint);
            return new Response("Flight delayed successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Error delaying flight", Status.INTERNAL_SERVER_ERROR);
        }

    }

    public static Response FlightAddPassenger(String passengerId, String flightId) {
        try {
            long lPassengerId;

            try {
                
                lPassengerId = Long.parseLong(passengerId);
                
            } catch (Exception ex) {
                return new Response("Passenger Id has to be numeric", Status.INTERNAL_SERVER_ERROR);
            }
            
            FlightStorage Fstorage = FlightStorage.getInstance();
            PassengerStorage Pstorage = PassengerStorage.getInstance();

            Flight flight = Fstorage.getFlight(flightId);
            if (flight == null) {
                return new Response("Flight not founded", Status.NOT_FOUND);
            }

            Passenger passenger = Pstorage.getPassenger(lPassengerId);
            if (passenger == null) {
                return new Response("Passenger not founded", Status.NOT_FOUND);
            }

            if (flight.getNumPassengers() >= flight.getPlane().getMaxCapacity()) {
                return new Response("Flight is full", Status.BAD_REQUEST);
            }
            if (flight.getPassengers().contains(passenger)) {
                return new Response("You are already in this flight", Status.BAD_REQUEST);
            }
            flight.addPassenger(passenger);
            passenger.addFlight(flight);
            return new Response("Passenger added", Status.OK);
        } catch (Exception ex) {
            return new Response("Error adding passenger", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static void ShowMyFlights(String passengerId, JTable table) {
        long LpassengerId;

        LpassengerId = Long.parseLong(passengerId);

        PassengerStorage Pstorage = PassengerStorage.getInstance();
        
        Passenger passenger = Pstorage.getPassenger(LpassengerId);
        if (passenger == null) {
            System.out.println("Passenger not founded");
        }
        List<Flight> flights = passenger.getFlights();
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Departure Date", "Arrival Date"}, 0);
        for (Flight flight : flights) {
            model.addRow(new Object[]{flight.getId(), flight.getDepartureDate(), CalcArrivalDateFlight.calculateArrivalDate(flight)});
        }

        table.setModel(model);
    }

}
