package com.amitshekhar.example.data.remote;

import android.content.Context;

import com.amitshekhar.example.data.listeners.DataListener;

import org.json.JSONObject;

public interface AllApiInterface {

    public void getApiRequest(Context context, JSONObject jsonObject, int event, DataListener dataListener);

}
