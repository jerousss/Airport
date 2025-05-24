/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Json;

import core.models.Passenger;
import core.models.storage.PassengerStorage;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class RJSONPassenger {

    public static void rJsonPassenger(){
        try{
            JSONArray ListJson = RJSON.load("json/passengers.json");
            for (int i = 0; i < ListJson.length(); i++) {
                JSONObject obj = ListJson.getJSONObject(i);
                
                Passenger ps = new Passenger(obj.getLong("id"),obj.getString("firstname"),obj.getString("lastname"), LocalDate.parse(obj.getString("birthDate")), obj.getInt("countryPhoneCode"),obj.getLong("phone"), obj.getString("country"));
                
                PassengerStorage.getInstance().addPassenger(ps);   
            }
        } catch(Exception ex){
            System.out.println("Reading error");
        }
    }
}
