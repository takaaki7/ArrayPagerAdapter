package com.demo.arraypargeradapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.fragment_state_btn, R.id.fragment_btn, R.id.view_btn})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_state_btn:
                startActivity(new Intent(this, FragmentStatePagerAdapterActivity.class));
                break;
            case R.id.fragment_btn:
                startActivity(new Intent(this, FragmentPagerAdapterActivity.class));
                break;
            case R.id.view_btn:
                startActivity(new Intent(this, ViewPagerAdapterActivity.class));
                break;
        }
    }

}
