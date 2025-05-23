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
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author USER
 */
public class RJSONFlight {

    public void loadFileLocation() {
        fillFlights("json/locations.json");
    }

    private void fillFlights(String json) {
        FlightStorage flightStorage = FlightStorage.getInstance();
        LocationStorage locationStorage = LocationStorage.getInstance();
        PlaneStorage planeStorage = PlaneStorage.getInstance();

        try (FileReader reader = new FileReader(json)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                String planeId = obj.getString("plane");
                String departureLocationId = obj.getString("departureLocation");
                String arrivalLocationId = obj.getString("arrivalLocation");
                String scaleLocationId = obj.isNull("scaleLocation") ? null : obj.getString("scaleLocation");
                String departureDateStr = obj.getString("departureDate");

                int hoursDurationArrival = obj.getInt("hoursDurationArrival");
                int minutesDurationArrival = obj.getInt("minutesDurationArrival");
                int hoursDurationScale = obj.getInt("hoursDurationScale");
                int minutesDurationScale = obj.getInt("minutesDurationScale");

                Plane plane;
                if (planeStorage != null) {
                    plane = planeStorage.getPlane(planeId);
                } else {
                    plane = null;
                }

                Location departureLocation = locationStorage.getLocation(departureLocationId);
                Location arrivalLocation = locationStorage.getLocation(arrivalLocationId);
                Location scaleLocation = null;
                if (scaleLocationId != null) {
                    scaleLocation = locationStorage.getLocation(scaleLocationId);
                }

                LocalDateTime departureDate = LocalDateTime.parse(departureDateStr);

                Flight flight;
                if (plane == null || departureLocation == null || arrivalLocation == null) {
                    System.err.println("Skipping flight " + id + " due to missing plane or location references. Plane: " + planeId + ", Departure: " + departureLocationId + ", Arrival: " + arrivalLocationId);
                    continue;
                }
                if (scaleLocationId != null && scaleLocation == null) {
                    System.err.println("Skipping flight " + id + " due to missing scale location reference: " + scaleLocationId);
                    continue;
                }

                if (scaleLocation != null) {
                    flight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate,
                            hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
                } else {
                    flight = new Flight(id, plane, departureLocation, arrivalLocation, departureDate,
                            hoursDurationArrival, minutesDurationArrival);
                }
                flightStorage.addFlight(flight);
            }
            System.out.println("Flights loaded successfully: " + json);
        } catch (IOException e) {
            System.err.println("Error reading flights file (" + json + "): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing flights JSON (" + json + "): " + e.getMessage() + ". Check data consistency.");
            e.printStackTrace();
        }
    }

}
