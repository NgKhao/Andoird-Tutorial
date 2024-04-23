package com.example.myfirstproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner, spinner2;
    private CheckBox ck1, ck2, ck3;
    private RadioButton g1, g2;
    private RatingBar rt;
    private Button btht;
    private TextView kq;

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

        initView();

        btht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = "Check box: ";

                if(ck1.isChecked()) // ck1 được chọn
                    ss += ck1.getText() + ", ";
                if(ck2.isChecked()) // ck2 được chọn
                    ss += ck2.getText() + ", ";
                if(ck3.isChecked()) // ck3 được chọn
                    ss += ck3.getText() + ", ";
                if(ss.endsWith(", "))
                    ss = ss.substring(0, ss.length() - 2); //trừ 2 là trừ cho "," và " "

                ss += "\nGioi tinh: ";
                if(g1.isChecked())
                    ss += g1.getText() + ". ";
                else if(g2.isChecked())
                    ss += g2.getText() + ". ";

                ss += "\nRating: " + rt.getRating();

                ss += "\n" + spinner.getSelectedItem().toString();

                kq.setText(ss);
            }

        });
    }

    private void initView() {
        ck1 = findViewById(R.id.ck1);
        ck2 = findViewById(R.id.ck2);
        ck3 = findViewById(R.id.ck3);
        g1 = findViewById(R.id.gNam);
        g2 = findViewById(R.id.gNu);
        rt = findViewById(R.id.rating);
        btht = findViewById(R.id.bt);
        kq = findViewById(R.id.kqInRa);

    }
}