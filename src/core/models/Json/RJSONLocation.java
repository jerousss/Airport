/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Json;

import core.models.Location;
import core.models.storage.LocationStorage;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author USER
 */
public class RJSONLocation {

    public void loadFileLocation() {
        fillLocations("json/locations.json");
    }

    private void fillLocations(String json) {

        LocationStorage locationStorage = LocationStorage.getInstance();
        try (FileReader reader = new FileReader(json)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String airportId = obj.getString("airportId");
                String airportName = obj.getString("airportName");
                String airportCity = obj.getString("airportCity");
                String airportCountry = obj.getString("airportCountry");
                double airportLatitude = obj.getDouble("airportLatitude");
                double airportLongitude = obj.getDouble("airportLongitude");

                Location location = new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
                locationStorage.addLocation(location);
            }
            System.out.println("Locations loaded successfully: " + json);
        } catch (IOException e) {
            System.err.println("Error reading locations file (" + json + "): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing locations JSON (" + json + "): " + e.getMessage());
        }
    }
}
