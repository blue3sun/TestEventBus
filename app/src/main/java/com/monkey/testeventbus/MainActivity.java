package com.monkey.testeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity{

    private Button mBtnSkip;
    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registEventBus();
    }

    private void registEventBus() {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }
    //onEventMainThread()具体在哪个线程执行是看注解中的threadMode的值 从源码EventBus的post(Object event)可以看出来
    @Subscribe(threadMode = ThreadMode.MAIN)
    //@Subscribe //如果不指名threadMode = ThreadMode.MAIN那么如果在子线程发布的事件则会出现异常：在子线程中操作了UI
    //public void onEventMainThread(FirstEvent event) {
    //EventBus 3.0方法名无所谓叫什么名字了?? 貌似是的，因为源码中是用的反射调用有Subscribe注解的方法，与方法名无关
    public void mainThread(FirstEvent event) {
        mTvInfo.setText(event.toString() + "\n" +
                "Looper.getMainLooper()==Looper.myLooper():"+ (Looper.getMainLooper()==Looper.myLooper()));
    }


    private void initView() {
        mBtnSkip = (Button)findViewById(R.id.btn_skip);
        mTvInfo = (TextView)findViewById(R.id.tv_info);
        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
