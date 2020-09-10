package com.example.tankapp.utils;


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
}
