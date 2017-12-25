package com.refresh.demo.mvc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.refresh.demo.R;
import com.refresh.demo.mvc.activity.CommonMVCHelperActivity;
import com.refresh.demo.mvc.activity.ComplexMVCHelperActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.mvc_helper_common_btn).setOnClickListener(this);
        findViewById(R.id.mvc_helper_complex_btn).setOnClickListener(this);
        findViewById(R.id.btn_test).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mvc_helper_common_btn:
                startActivity(new Intent(this, CommonMVCHelperActivity.class));
                break;
            case R.id.mvc_helper_complex_btn:
                startActivity(new Intent(this, ComplexMVCHelperActivity.class));
                break;
            case R.id.btn_test:
                startActivity(new Intent(this, TestActivity.class));
                break;
        }
    }
}
