package com.monkey.testeventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnFirst;
    private Button mBtnSecond;
    private Button mBtnThrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        mBtnFirst = (Button)findViewById(R.id.btn_first);
        mBtnSecond = (Button)findViewById(R.id.btn_second);
        mBtnThrid = (Button)findViewById(R.id.btn_third);
        mBtnFirst.setOnClickListener(this);
        mBtnSecond.setOnClickListener(this);
        mBtnThrid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_first:
                EventBus.getDefault().post(new FirstEvent("FirstEvent:This is a cat!!!"));
                break;
            case R.id.btn_second:
                EventBus.getDefault().post(new SecondEvent("SecondEvent:This is a dog!!!"));
                break;
            case R.id.btn_third:
                break;
            default:
                break;

        }
    }
}
