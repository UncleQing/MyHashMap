package com.example.t.myhashmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private MyHashMap<Integer, String> mMyHashMap;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn_main);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyHashMap.put(new Random().nextInt(100), Integer.toString(new Random().nextInt(100)));
            }
        });

        mMyHashMap = new MyHashMap<>();
    }
}
