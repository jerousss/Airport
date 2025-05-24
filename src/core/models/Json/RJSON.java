/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.Json;

import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONTokener;

/**
 *
 * @author USER
 */
public class RJSON {
    public static JSONArray load(String path) {
       InputStream is = RJSON.class.getClassLoader().getResourceAsStream(path);
       JSONTokener tokener = new JSONTokener(is);
     return new JSONArray(tokener);
    }
}
