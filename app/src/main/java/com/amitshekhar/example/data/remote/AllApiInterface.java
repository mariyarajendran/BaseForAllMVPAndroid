package com.amitshekhar.example.data.remote;

import android.content.Context;

import com.amitshekhar.example.data.listeners.DataListener;

public interface AllApiInterface {

    public void getApiRequest(Context context, DataListener dataListener);

}
