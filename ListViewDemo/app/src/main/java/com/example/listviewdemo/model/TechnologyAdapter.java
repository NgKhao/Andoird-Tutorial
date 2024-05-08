package com.example.listviewdemo.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listviewdemo.R;

public class TechnologyAdapter extends ArrayAdapter<Technology> {
    private Context context;
    private Technology[] mList;
    public TechnologyAdapter(@NonNull Context context, Technology[] mList) {
        super(context, R.layout.item_tech, mList);
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        inflate(thổi phồng) layout thành các đối tượng view nên theo mình nghĩ chỉ cần 1 item mẫu là được
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_tech, null, true );
        ImageView img = view.findViewById(R.id.img);
        TextView tname = view.findViewById(R.id.tname);
        TextView tsub = view.findViewById(R.id.tsub);
        TextView tdecribe = view.findViewById(R.id.tdecribe);
        Technology t = mList[position];
        img.setImageResource(t.getImg());
        tname.setText(t.getName());
        tsub.setText(t.getSub());
        tdecribe.setText(t.getDerscribe());

        return view;
    }

    public Technology getItem(int position){
        return mList[position];
    }
}
