/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationStorage;

/**
 *
 * @author GERONIMO
 */
public class LocationController {

    public static Response createLocation(String airportId, String airportName, String airportCity, String airportCountry, String airportLatitude, String airportLongitude) {
        try {
            double doubleAirportLatitude, doubleAirportLongitude;
           // falta validacion decimales 
            try {
                doubleAirportLatitude = Double.parseDouble(airportLatitude);
                if (doubleAirportLatitude < 90 && doubleAirportLatitude > -90) {
                    return new Response("Id must be in the range(-90,90)", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            try {
                doubleAirportLongitude = Double.parseDouble(airportLongitude);
                if (doubleAirportLongitude < 180 && doubleAirportLongitude > -180) {
                    return new Response("Id must be in the range(-180,180)", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (airportId.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }

            if (airportName.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }

            if (airportCity.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }

            if (airportCountry.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }

            LocationStorage storage = LocationStorage.getInstance();
            if (!storage.addFlight(new Location(airportId, airportName, airportCity, airportCountry, doubleAirportLatitude, doubleAirportLongitude))) {
                return new Response("An Airport with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Location created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
