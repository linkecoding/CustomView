package com.codekong.customview.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.codekong.customview.R;
import com.codekong.customview.view.FlowLayout;
import com.codekong.customview.view.TextFlashingTextView;
import com.codekong.customview.view.TopBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextFlashingTextView textView = (TextFlashingTextView) findViewById(R.id.mytv);
        textView.setMiddleColor(Color.GREEN);

        FlowLayout fl = (FlowLayout) findViewById(R.id.id_fl);
        //fl.addView(childView);

        TopBar topBar = (TopBar) findViewById(R.id.id_topbar);
        //设置TopBar左右按钮的点击事件
        topBar.setTopbarClickListener(new TopBar.TopbarClickListener() {
            @Override
            public void leftClick(View v) {
                //左边按钮点击事件
            }

            @Override
            public void rightClick(View v) {
                //右边按钮点击事件
            }
        });

        //显示左边按钮
        topBar.setBtnVisibility(0, true);
        //隐藏右边按钮
        topBar.setBtnVisibility(1, false);
    }
}
