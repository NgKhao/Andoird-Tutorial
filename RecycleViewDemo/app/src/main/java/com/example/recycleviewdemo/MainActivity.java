package com.example.recycleviewdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleviewdemo.model.Cat;
import com.example.recycleviewdemo.model.CatAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener{
    private RecyclerView recyclerView;
    protected CatAdapter adapter;
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

        recyclerView = findViewById(R.id.rview);

        adapter = new CatAdapter(getList());
        adapter.setCatItemListener(this);
        GridLayoutManager manager = new GridLayoutManager(this, 3); // spanCount 3 cột
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private List<Cat> getList(){
        List<Cat> list = new ArrayList<>();
        list.add(new Cat(R.drawable.cat1, "Mèo con 1"));
        list.add(new Cat(R.drawable.cat2, "Mèo con 2"));
        list.add(new Cat(R.drawable.cat3, "Mèo con 3"));
        list.add(new Cat(R.drawable.cat4, "Mèo con 4"));
        list.add(new Cat(R.drawable.cat5, "Mèo con 5"));
        list.add(new Cat(R.drawable.cat6, "Mèo con 6"));
        return  list;

    }

    @Override
    public void onItemClick(View view, int postion) {
        Cat c = getList().get(postion);
        Toast.makeText(this, c.getName(), Toast.LENGTH_SHORT).show();
    }
}