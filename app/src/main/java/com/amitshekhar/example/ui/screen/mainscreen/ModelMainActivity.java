package com.amitshekhar.example.ui.screen.mainscreen;

import org.json.JSONObject;

public class ModelMainActivity {
    JSONObject jsonObjectLoginData;


    public void parseLoginJsonData() {
        try {
            jsonObjectLoginData = new JSONObject();
            jsonObjectLoginData.put("user_mobilenumber", "8988998998");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
