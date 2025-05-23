/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Sresponsability;

import core.models.Flight;
import java.time.LocalDateTime;

/**
 *
 * @author USER
 */
public class CalcArrivalDateFlight {
    private Flight flight;

    public CalcArrivalDateFlight(Flight flight) {
        this.flight = flight;
    }
    
    public static LocalDateTime calculateArrivalDate(Flight flight) {
        return flight.getDepartureDate().plusHours(flight.getHoursDurationScale() + flight.getHoursDurationArrival()).plusMinutes(flight.getMinutesDurationScale() + flight.getMinutesDurationArrival());
    }
}