package com.example.tankapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        List<String> Tank1 = new ArrayList<>();
        Tank1.add("0.7 km");
        Tank1.add("E10: 1,29€");
        Tank1.add("E5: 1,24€");
        Tank1.add("Diesel: 1,02€");

        List<String> Tank2 = new ArrayList<>();
        Tank2.add("0.3 km");
        Tank2.add("E10: 1,29€");
        Tank2.add("E5: 1,24€");
        Tank2.add("Diesel: 1,02€");

        List<String> Tank3 = new ArrayList<>();
        Tank3.add("1.7 km");
        Tank3.add("E10: 1,29€");
        Tank3.add("E5: 1,24€");
        Tank3.add("Diesel: 1,02€");

        expandableListDetail.put("ED", Tank1);
        expandableListDetail.put("TOTAL", Tank2);
        expandableListDetail.put("ARAL", Tank3);
        return expandableListDetail;
    }
}
