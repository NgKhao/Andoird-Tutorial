package com.example.a42batsukien;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class DateTimeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et;
    private EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_date_time);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et = findViewById(R.id.eTime);
        ed = findViewById(R.id.eDate);
        et.setOnClickListener(this);
        ed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == et){
            Calendar c = Calendar.getInstance(); // lấy thông tin thời gian hiện tại
            int hh = c.get(Calendar.HOUR_OF_DAY);
            int mm = c.get(Calendar.MINUTE);
//            tạo ra một hộp thoại và chọn thời gian
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    et.setText(hourOfDay + ":" + minute);
                }
            }, hh, mm, false);
            timePickerDialog.show();
        }
        if(v == ed){
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH); // DATE và DAYOFMONTH đều được vì là ngày trong tháng
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    ed.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            }, y, m, d);
            datePickerDialog.show();
        }
    }
}