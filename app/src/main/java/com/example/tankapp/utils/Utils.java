package com.example.tankapp.utils;


import android.util.Log;

import com.example.tankapp.objects.Gasstation;

import org.json.JSONException;
import org.json.JSONObject;

//this class contains only static methods
public class Utils {


    //get the current API String
    public static String getAPIString(String psLat,String psLng, String psRadius)
    {
        //String url = "https://creativecommons.tankerkoenig.de/json/list.php?lat=52.521&lng=13.438&rad=10&sort=dist&type=all&apikey=00000000-0000-0000-0000-000000000002";
        String url = "https://creativecommons.tankerkoenig.de/json/list.php?lat=" + psLat+
                "&lng="+ psLng+
                "&rad="+ psRadius+
                "&sort=dist&type=all&apikey=4d78ed57-c1c5-2e89-8e82-c8651b3a437c";
        return url;
    }


    //create a gasstation
    public static Gasstation getGasStation(JSONObject pkJsonObj){
        try
        {
            Gasstation gasstation = new Gasstation();
            gasstation.setName      (pkJsonObj.getString ("name"         ));
            gasstation.setId        (pkJsonObj.getString ("id"           ));
            gasstation.setBrand     (pkJsonObj.getString ("brand"        ));
            gasstation.setDist      (pkJsonObj.getDouble ("dist"         ));
            gasstation.setStreet    (pkJsonObj.getString ("street"       ));
            gasstation.setPlace     (pkJsonObj.getString ("place"        ));
            gasstation.setLat       (pkJsonObj.getDouble ("lat"          ));
            gasstation.setLng       (pkJsonObj.getDouble ("lng"          ));
            gasstation.setDiesel    (pkJsonObj.getDouble ("diesel"       ));
            gasstation.setE5        (pkJsonObj.getDouble ("e5"           ));
            gasstation.setE10       (pkJsonObj.getDouble ("e10"          ));
            gasstation.setOpen      (pkJsonObj.getBoolean("isOpen"       ));
            gasstation.setHousnumber(pkJsonObj.getString ("houseNumber"  ));
            gasstation.setPostCode  (pkJsonObj.getInt    ("postCode"     ));
            return gasstation;
        }
        catch (JSONException e) {
            Log.e("Error JSON", "Could not parse malformed JSON: \"");
            return null;
        }
    }
}
