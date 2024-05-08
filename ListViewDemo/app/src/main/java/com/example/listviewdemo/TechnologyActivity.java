package com.example.listviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.listviewdemo.model.Technology;
import com.example.listviewdemo.model.TechnologyAdapter;

public class TechnologyActivity extends AppCompatActivity {


    private ListView listView;
    TechnologyAdapter adapter;
    private Technology[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_technology);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.lview);

        initData();

//        truyền context, list vào để xử lý trong TechnologyAdapter
        adapter = new TechnologyAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i=0; i<listView.getAdapter().getCount(); i++){
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    //return view tại vị trí được chỉ định sau đó set màu nền
                }
                listView.getChildAt(position).setBackgroundColor(Color.YELLOW);
                Technology t = adapter.getItem(position);
                Toast.makeText(TechnologyActivity.this, t.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        int[] imgs = {R.drawable.androidlogo, R.drawable.images,
                R.drawable.blackberry, R.drawable.window};
        String[] names = {"Android", "Ios", "BlackBerry", "Window"};
        String[] subs = {"Sub Android", "Sub Ios", "Sub BlackBerry", "Sub Window"};
        String[] descs = {"Mt Android", "Mt Ios", "Mt BlackBerry", "Mt Window"};
        list = new Technology[imgs.length];
        for(int i=0; i<list.length; i++){
            list[i] = new Technology(imgs[i], names[i], subs[i], descs[i]);
        }
    }
}