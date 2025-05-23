/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Json;

import core.models.Plane;
import core.models.storage.PlaneStorage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author USER
 */
public class RJSONPlane {

    public void loadFilePlane() {
        fillPlanes("json/locations.json");
    }

    public void fillPlanes(String json) {
        PlaneStorage planeRepo = PlaneStorage.getInstance();
        try (FileReader reader = new FileReader(json)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                String brand = obj.getString("brand");
                String model = obj.getString("model");
                int maxCapacity = obj.getInt("maxCapacity");
                String airline = obj.getString("airline");

                Plane plane = new Plane(id, brand, model, maxCapacity, airline);
                
                if (planeRepo != null) { 
                    planeRepo.addPlane(plane);
                } else {
                    System.err.println("PlaneRepository is null. Cannot add plane.");
                }
            }
            System.out.println("Planes loaded successfully: " + json);
        } catch (IOException e) {
            System.err.println("Error reading planes file (" + json + "): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing planes JSON (" + json + "): " + e.getMessage());

        }
    }
}
