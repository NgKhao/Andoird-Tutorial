package com.example.myfirstproject;

import static java.lang.Double.parseDouble;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculateActivity extends AppCompatActivity{

    private TextView result;
    private EditText editText1, editText2;
    private Button buttonAdd;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

//        bắt sự kiện nút, nếu nhiều nút thì phải implements onclicklistener
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nn1 = editText1.getText().toString();
                String nn2 = editText2.getText().toString();
                double n1,n2;
                try{
                    //        change String to double
                    n1 = parseDouble(nn1);
                    n2 = parseDouble(nn2);
                    String kk = calcullate(n1, n2, "+");
                    result.setText(kk);
//            https://viblo.asia/p/android-toast-eXoGWkNQGLO : giải thích về Toast
                    Toast.makeText(getApplicationContext(),kk,Toast.LENGTH_LONG).show();
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"vui lòng nhập 2 số", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        bắt sự kiến spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nn1 = editText1.getText().toString();
                String nn2 = editText2.getText().toString();
                double n1,n2;
                try{
                    //        change String to double
                    n1 = parseDouble(nn1);
                    n2 = parseDouble(nn2);
                    String p = spinner.getSelectedItem().toString();
                    String kk = calcullate(n1, n2, p);
                    result.setText(kk);
//            https://viblo.asia/p/android-toast-eXoGWkNQGLO : giải thích về Toast
                    Toast.makeText(getApplicationContext(),kk,Toast.LENGTH_LONG).show();
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"vui lòng nhập 2 số", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        result = findViewById(R.id.kq);
        editText1 = findViewById(R.id.e1);
        editText2 = findViewById(R.id.e2);
        buttonAdd = findViewById(R.id.btAdd);
        spinner = findViewById(R.id.sp);
    }

//    public void add(View view) {
//        String nn1 = editText1.getText().toString();
//        String nn2 = editText2.getText().toString();
//        double n1,n2;
//        try{
//            //        change String to double
//            n1 = parseDouble(nn1);
//            n2 = parseDouble(nn2);
//            String kk = calcullate(n1, n2, "+");
//            result.setText(kk);
////            https://viblo.asia/p/android-toast-eXoGWkNQGLO : giải thích về Toast
//            Toast.makeText(this,kk,Toast.LENGTH_LONG).show();
//        }catch (NumberFormatException e){
//        }
//    }

    private String calcullate(double x, double y, String p){
        String s = "";
        switch (p){
            case "+":
                s = "Tong: " + (x + y);
                break;
            case "-":
                s = "Hieu: " + (x - y);
                break;
            case "x":
                s = "Nhan: " + (x * y);
                break;
            case ":":
                if(y == 0)
                    s = "Khong chia cho 0";
                else
                    s = "Chia: " + (x / y);
                break;
        }
        return s;
    }
}