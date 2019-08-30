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

package com.amitshekhar.example.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.amitshekhar.example.R;
import com.amitshekhar.example.network.NetworkMvpView;
import com.amitshekhar.example.network.NetworkPresenter;
import com.amitshekhar.example.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainMvpView, NetworkMvpView {

    @Inject
    MainPresenter<MainMvpView> mMainPresenter;
    @Inject
    NetworkPresenter<NetworkMvpView> mNetworkPresenter;

    @BindView(R.id.textViewData)
    TextView textViewData;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter.attachView(this);
        mNetworkPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
        mNetworkPresenter.detachView();
    }

    @Override
    public void showData(String data) {
        textViewData.setText(data);
    }

    @Override
    public void showError(String error) {
        textViewData.setText(error);
    }

    @OnClick(R.id.btnLoadData)
    void btnLoadData() {
        mNetworkPresenter.getNetworkStatus(getApplicationContext());
    }

    @Override
    public void showNetworkStatus(boolean networkStatus) {
        handleNetworkStatus(networkStatus);
    }


    public void handleNetworkStatus(boolean status) {
        if (status) {
            mMainPresenter.getData();
        } else {
            Toast.makeText(getApplicationContext(), "Network Not found", Toast.LENGTH_SHORT).show();
        }
    }
}
