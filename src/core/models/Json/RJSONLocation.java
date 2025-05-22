/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Json;

import core.models.Location;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 *
 * @author USER
 */
public class RJSONLocation implements RJSON.RJson<Location> {

    @Override
    public List<Location> readFromFile(String relativePath) {
       List<Location> locations = new ArrayList<>();

        try {
            File file = new File(relativePath);
            if (!file.exists()) {
                System.err.println("Archivo no encontrado: " + relativePath);
                 if (locations instanceof Location) {
                return locations;}
            }

            String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                 String airportId = obj.getString("airportId");
                    String airportName = obj.getString("airportName");
                    String airportCity = obj.getString("airportCity");
                    String airportCountry = obj.getString("airportCountry");
                    long airportLatitude = obj.getLong("airportLatitude");
                    long airportLongitude = obj.getLong("airportLatitude");

                    Location location = new Location(
                            airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude
                    );


                locations.add(location);
            }

        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error procesando JSON: " + e.getMessage());
        }

        return locations;
    }

   
    
 

   
     
}
