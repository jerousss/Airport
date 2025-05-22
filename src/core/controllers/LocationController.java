/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationStorage;
import java.math.BigDecimal;

/**
 *
 * @author GERONIMO
 */
public class LocationController {

    public static Response createLocation(String airportId, String airportName, String airportCity, String airportCountry, String airportLatitude, String airportLongitude) {
        try {
            
            if (airportId.equals("")) {
                return new Response("id must be not empty", Status.BAD_REQUEST);
            }
            if (airportId.length() > 3) {
                return new Response("id must have max 3 letters", Status.BAD_REQUEST);
            }
            if(!Character.isUpperCase(airportId.charAt(0)) || !Character.isUpperCase(airportId.charAt(1)) || !Character.isUpperCase(airportId.charAt(2))) {
                return new Response("id must have 3 uppercase letters", Status.BAD_REQUEST);
            }
            
            if (airportName.equals("")) {
                return new Response("Airport name must be not empty", Status.BAD_REQUEST);
            }
            
            if (airportCity.equals("")) {
                return new Response("Airport city must be not empty", Status.BAD_REQUEST);
            }
            
            if (airportCountry.equals("")) {
                return new Response("Aiport country must be not empty", Status.BAD_REQUEST);
            }
            
            
            if (airportLatitude.equals("")) {
                return new Response("Aiport Latitude must be not empty", Status.BAD_REQUEST);
            }
            
            double doubleAirportLatitude, doubleAirportLongitude;
            try {
                doubleAirportLatitude = Double.parseDouble(airportLatitude);
                if (doubleAirportLatitude > 90 || doubleAirportLatitude < -90) {
                    return new Response("Latitude must be in the range(-90,90)", Status.BAD_REQUEST);
                }
                
                if (!airportLatitude.matches("-?\\d+(\\.\\d{1,4})?")) {
                    return new Response("Latitude only can have 4 decimals or less", Status.BAD_REQUEST);
                }
                
            } catch (NumberFormatException ex) {
                return new Response("Latitude must be numeric", Status.BAD_REQUEST);
            }
            
            if (airportLongitude.equals("")) {
                    return new Response("Aiport Longitude must be not empty", Status.BAD_REQUEST);
            }
            try {
                doubleAirportLongitude = Double.parseDouble(airportLongitude);
                if (doubleAirportLongitude > 180 || doubleAirportLongitude < -180) {
                    return new Response("Longitude must be in the range(-180,180)", Status.BAD_REQUEST);
                }
                
                if (!airportLongitude.matches("-?\\d+(\\.\\d{1,4})?")) {
                    return new Response("Longitude only can have 4 decimals or less", Status.BAD_REQUEST);
                }
                
            } catch (NumberFormatException ex) {
                
                return new Response("Longitude must be numeric", Status.BAD_REQUEST);
            }

            

            
            LocationStorage storage = LocationStorage.getInstance();
            if (!storage.addLocation(new Location(airportId, airportName, airportCity, airportCountry, doubleAirportLatitude, doubleAirportLongitude))) {
                return new Response("An Airport with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Location created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
