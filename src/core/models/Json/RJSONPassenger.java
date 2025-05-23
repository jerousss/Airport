/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Json;

import core.models.Location;
import core.models.Passenger;
import core.models.storage.LocationStorage;
import core.models.storage.PassengerStorage;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author USER
 */
public class RJSONPassenger {

    public void loadFilePassenger() {
        fillPassengers("json/locations.json");
    }

    private void fillPassengers(String json) {
        PassengerStorage passengerStorage = PassengerStorage.getInstance();

        try (FileReader reader = new FileReader(json)) {
            JSONArray array = new JSONArray(new JSONTokener(reader));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                long id = obj.getLong("id");
                String firstName = obj.getString("firstname");
                String lastName = obj.getString("lastname");
                String sBirthDate = obj.getString("birthDate");
                int code = obj.getInt("contryPhoneCode");
                long phone = obj.getLong("phone");
                String country = obj.getString("country");

                LocalDate birthDate = LocalDate.parse(sBirthDate);

                Passenger passenger = new Passenger(id, firstName, lastName, birthDate, code, phone, country);

                passengerStorage.addPassenger(passenger);
            }
            System.out.println("Locations loaded successfully: " + json);
        } catch (IOException e) {
            System.err.println("Error reading locations file (" + json + "): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error parsing locations JSON (" + json + "): " + e.getMessage());
        }
    }
}
