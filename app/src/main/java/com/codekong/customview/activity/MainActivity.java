package com.codekong.customview.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codekong.customview.R;
import com.codekong.customview.view.TextFlashingTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextFlashingTextView textView = (TextFlashingTextView) findViewById(R.id.mytv);
        textView.setMiddleColor(Color.GREEN);
    }
}
