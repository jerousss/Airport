/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Flight;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author USER
 */
public final class FlightStorage {

    private static FlightStorage instance;
    
    private ArrayList<Flight> flights;

    private FlightStorage() {
        this.flights = new ArrayList<>();
    }
   public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }

    public boolean addFlight(Flight flight) {
        for (Flight f : this.flights) {
            if (f.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }
    
    public Flight getFlight(String id){
        for (Flight f : this.flights) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }
    
    public ArrayList<Flight> getFlights() {
        ArrayList<Flight> same = new ArrayList<>();
        for(Flight fl : flights) {
            same.add(fl.clone());
        }
        return same;
    }
    
    public ArrayList<Flight> getFlightsSorted() {
     ArrayList<Flight> same = new ArrayList<>();
    for (Flight f : this.flights) {
        same.add(f.clone());
    }
    Collections.sort(same, Comparator.comparing(Flight::getDepartureDate));
    return same;
}
    
    public boolean delFlight(String id){
        for (Flight f : this.flights) {
            if (f.getId().equals(id)) {
                this.flights.remove(f);
                return true;
            }
        }
        return false;
    }
    
    

}
