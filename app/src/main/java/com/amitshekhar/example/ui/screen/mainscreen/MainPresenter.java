/*
 *    Copyright (C) 2018 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.amitshekhar.example.ui.screen.mainscreen;

import com.amitshekhar.example.data.listeners.DataListener;
import com.amitshekhar.example.data.ServiceRequest;
import com.amitshekhar.example.ui.base.BasePresenter;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by amitshekhar on 13/01/17.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {


    ServiceRequest serviceRequest;


    @Inject
    public MainPresenter(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }


    @Override
    public void getData() {

    }

    @Override
    public void getVolleyRequest(JSONObject jsonObject, int event) {
        getMvpView().showLoading();
        serviceRequest.getApiRequest(getContext(), jsonObject, event, new DataListener() {
            @Override
            public void onResponse(String data) {
                getMvpView().hideLoading();
                getMvpView().showData(data);
            }

            @Override
            public void onError(String error) {
                getMvpView().hideLoading();
                getMvpView().showData(error);
            }
        });

    }
}
