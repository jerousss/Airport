    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Json;

import core.models.Plane;
import core.models.storage.PlaneStorage;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 *
 * @author USER
 */
public class RJSONPlane {

    public static void rJsonPlane(){
        try{
            JSONArray ListJson = RJSON.load("json/planes.json");
            for (int i = 0; i < ListJson.length(); i++) {
                JSONObject obj = ListJson.getJSONObject(i);
                
                Plane p = new Plane( obj.getString("id"), obj.getString("brand"), obj.getString("model"), obj.getInt("maxCapacity"), obj.getString("airline"));
                
                PlaneStorage.getInstance().addPlane(p);   
            }
        } catch(Exception ex){
            System.out.println("Reading error");
        }
    }
}
