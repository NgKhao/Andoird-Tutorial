package com.example.rycycleviewdemo_crud;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rycycleviewdemo_crud.model.Cat;
import com.example.rycycleviewdemo_crud.model.CatAdapter;
import com.example.rycycleviewdemo_crud.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener,
        SearchView.OnQueryTextListener{

    private Spinner spinner;
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    private EditText eName, eDesc, ePrice;
    private Button btAdd, btUpdate;
    private int pcurr; // trả về vị trí đã click muốn update
    private SearchView searchView;
    private int[] imgs = new int[]{R.drawable.cat1, R.drawable.cat2, R.drawable.cat3,
            R.drawable.cat4, R.drawable.cat5, R.drawable.cat6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        catAdapter = new CatAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(catAdapter);
//        click vào 1 item bất kì để hiện in4
        catAdapter.setClickItemListener(this);
        searchView.setOnQueryTextListener(this);
//        bắt sự kiên nút Add
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Cat cat = new Cat();
                String i = spinner.getSelectedItem().toString();// trả về index của của vì
//                trong class SpinnerAdapter mình đã tự set getItem()
                String name = eName.getText().toString(); //getText() => lấy văn bản đã được nhập
                String desc = eDesc.getText().toString();
                String p = ePrice.getText().toString();
                int img = 0;
                double price = 0;
                try {
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    Cat cat = new Cat(img, name, desc, price);
                    catAdapter.add(cat);
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Cat cat = new Cat();
                String i = spinner.getSelectedItem().toString();// trả về index của của vì
//                trong class SpinnerAdapter mình đã tự set getItem()
                String name = eName.getText().toString(); //getText() => lấy văn bản đã được nhập
                String desc = eDesc.getText().toString();
                String p = ePrice.getText().toString();
                int img = 0;
                double price = 0;
                try {
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    Cat cat = new Cat(img, name, desc, price);
                    catAdapter.update(pcurr, cat);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Nhap lai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void initView() {
        spinner = findViewById(R.id.img);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        spinner.setAdapter(adapter);
        recyclerView = findViewById(R.id.recycleView);
        eName = findViewById(R.id.name);
        eDesc = findViewById(R.id.describe);
        ePrice = findViewById(R.id.price);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);
    }

    //ghi đè của method TỰ TẠO
    @Override
    public void onItemClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        pcurr = position;
        Cat cat = catAdapter.getItem(position);
        int img = cat.getImg(); // trả về int của ảnh
        int p = 0;
        for(int i=0; i < imgs.length; i++){
            if(img == imgs[i]){
                p = i; // lấy index của ảnh
                break;
            }
        }
        spinner.setSelection(p);
        eName.setText(cat.getName());
        eDesc.setText(cat.getDescribe());
        ePrice.setText(cat.getPrice() + "");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText);
        return false;
    }

    private void filter(String newText) {
        List<Cat> filterlist = new ArrayList<>();
        for(Cat i : catAdapter.getListBackup()){
//            nếu tên của Cat khi chuyển thành chữ thường chứa
//            cái user nhập khi chuyển thành chữ thường
            if(i.getName().toLowerCase().contains(newText.toLowerCase())){
                filterlist.add(i);
            }
        }
        if(filterlist.isEmpty())
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        else
            catAdapter.filterList(filterlist);
    }
}