package com.example.a31;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        
        topBar.setOnTopbarClickListener(new TopBar.topbarClickListenter() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this, "aaa", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {

            }
        });
    }


    private void initView() {
        topBar = findViewById(R.id.topBar);
    }
}
