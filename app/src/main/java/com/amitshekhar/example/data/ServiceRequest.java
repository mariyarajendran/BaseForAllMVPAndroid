package com.amitshekhar.example.data;

import android.content.Context;

import com.amitshekhar.example.data.listeners.DataListener;
import com.amitshekhar.example.data.remote.AllApiInterface;
import com.amitshekhar.example.data.remote.MySingleton;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ServiceRequest implements AllApiInterface {

    @Inject
    ServiceRequest(){}

    public static String BASE_URL = "http://api.plos.org/";


    public static String SEARCH_DATA_URL = BASE_URL + "search?q=title:DNA";

    @Override
    public void getApiRequest(Context context, final DataListener dataListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, SEARCH_DATA_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dataListener.onResponse(response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dataListener.onError(error.toString());
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
