/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Json;

import core.models.Location;
import core.models.storage.LocationStorage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class RJSONLocation {

    public static void rJsonLocation() {
        try {
            JSONArray ListJson = RJSON.load("json/locations.json");
            for (int i = 0; i < ListJson.length(); i++) {
                JSONObject obj = ListJson.getJSONObject(i);

                Location l = new Location(obj.getString("airportId"), obj.getString("airportName"), obj.getString("airportCity"), obj.getString("airportCountry"), obj.getDouble("airportLatitude"), obj.getDouble("airportLongitude"));

                LocationStorage.getInstance().addLocation(l);
            }
        } catch (Exception ex) {
            System.out.println("Reading error");
        }
    }
}
