/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Json;

import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PlaneStorage;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class RJSONFlight {

    public static void rJsonFlight() {
        try {
            JSONArray ListJson = RJSON.load("json/flights.json");
            for (int i = 0; i < ListJson.length(); i++) {
                JSONObject obj = ListJson.getJSONObject(i);
                String plane = obj.getString("plane");
                String departureLoca = obj.getString("departureLocation");
                //String scaleLoca = obj.getString("scaleLocation");
                String arriveLoca = obj.getString("arrivalLocation");

                Plane p = PlaneStorage.getInstance().getPlane(plane);
                Location departureLocation = LocationStorage.getInstance().getLocation(departureLoca);
                Location arrivalLocation = LocationStorage.getInstance().getLocation(arriveLoca);
                //Location scaleLocation = LocationStorage.getInstance().getLocation(scaleLoca);

                Location LocationScale = null;  
                if (!obj.isNull("scaleLocation")) { 
                    String LocScale = obj.getString("scaleLocation");
                    LocationScale = LocationStorage.getInstance().getLocation(LocScale);
                }
                Flight flight;
                if(LocationScale == null){
                    flight = new Flight(obj.getString("id"), p, departureLocation, arrivalLocation, LocalDateTime.parse(obj.getString("departureDate")), obj.getInt("hoursDurationArrival"), obj.getInt("minutesDurationArrival"));
                }else{
                    flight = new Flight(obj.getString("id"), p, departureLocation, LocationScale, arrivalLocation, LocalDateTime.parse(obj.getString("departureDate")), obj.getInt("hoursDurationArrival"),obj.getInt("minutesDurationArrival"),obj.getInt("hoursDurationScale"),obj.getInt("minutesDurationScale"));
                }
                FlightStorage.getInstance().addFlight(flight);
            }
        } catch (Exception e) {
            System.out.println("Error reading flights file");
        }
    }
}
