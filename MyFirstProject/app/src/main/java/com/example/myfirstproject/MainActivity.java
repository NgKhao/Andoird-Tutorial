package com.example.myfirstproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner, spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_p2_bai1);
//        gọi view sp2 cho spinner
        spinner = findViewById(R.id.sp2);
        String[] list = {"HUST", "PTIT", "NEU", "FTU"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.item, list);
        spinner.setAdapter(adapter);

        spinner2 = findViewById(R.id.sp1);
        String[] list2 = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.item, list2);
        spinner2.setAdapter(adapter2);
    }
}