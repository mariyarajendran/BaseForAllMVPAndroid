

package com.amitshekhar.example.ui.screen.mainscreen;

import com.amitshekhar.example.ui.base.MvpPresenter;

import org.json.JSONObject;

/**
 * Created by amitshekhar on 13/01/17.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {


    void getData();

    void getVolleyRequest(JSONObject jsonObject, int event);

}
