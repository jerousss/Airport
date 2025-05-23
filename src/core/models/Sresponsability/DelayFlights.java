/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Sresponsability;

import core.models.Flight;

/**
 *
 * @author USER
 */
public class DelayFlights {
    private Flight flight;

    public DelayFlights(Flight flight) {
        this.flight = flight;
    }
    
    public static void delay(Flight flight, int hours, int minutes) {
        flight.setDepartureDate(flight.getDepartureDate().plusHours(hours).plusMinutes(minutes));
    }
}
