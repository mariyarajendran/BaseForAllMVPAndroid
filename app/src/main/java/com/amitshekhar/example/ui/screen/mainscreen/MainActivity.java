package com.amitshekhar.example.ui.screen.mainscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.amitshekhar.example.R;
import com.amitshekhar.example.network.NetworkMvpView;
import com.amitshekhar.example.network.NetworkPresenter;
import com.amitshekhar.example.ui.base.BaseActivity;
import com.amitshekhar.example.utils.CommonUtils;

import org.json.JSONException;

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

    CommonUtils commonUtils;
    ModelMainActivity modelMainActivity;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMain();
    }


    void initMain() {
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter.attachView(getApplicationContext(), this);
        mNetworkPresenter.attachView(getApplicationContext(), this);
        commonUtils = new CommonUtils(getApplicationContext());
        modelMainActivity = new ModelMainActivity();
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
            modelMainActivity.parseLoginJsonData();
            mMainPresenter.getVolleyRequest(modelMainActivity.jsonObjectLoginData, 0);
        } else {
            commonUtils.showToastLong(getString(R.string.no_network));
        }
    }
}
