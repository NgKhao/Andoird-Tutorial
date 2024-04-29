package com.example.a42batsukien;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class DateTimeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et;
    private EditText ed;
    private TextView tv;

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
        tv = findViewById(R.id.mMenu);
        registerForContextMenu(tv); //nhấn và giữ sẽ hiển thị menu ngữ cảnh trên view tv
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        dùng để kích hoạt 1 menu tên main_menu vào Menu
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mFile)
            Toast.makeText(this, "File", Toast.LENGTH_SHORT).show();
        else if(item.getItemId() == R.id.mEmail)
            Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
        else if(item.getItemId() == R.id.mPhone)
            Toast.makeText(this, "Phone", Toast.LENGTH_SHORT).show();
        else if(item.getItemId() == R.id.mExit)
            System.exit(0);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.color_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
            
        if(id == R.id.mRed)
            tv.setTextColor(Color.RED);
        else if (id == R.id.mBlue)
            tv.setTextColor(Color.BLUE);
        else if(id == R.id.mYellow)
            tv.setTextColor(Color.YELLOW);

        return super.onContextItemSelected(item);
    }
}