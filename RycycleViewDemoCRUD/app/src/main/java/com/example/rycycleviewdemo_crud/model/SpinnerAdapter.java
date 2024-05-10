package com.example.rycycleviewdemo_crud.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.rycycleviewdemo_crud.R;

public class SpinnerAdapter extends BaseAdapter {

    private int[] imgs = new int[]{R.drawable.cat1, R.drawable.cat2, R.drawable.cat3,
                                    R.drawable.cat4, R.drawable.cat5, R.drawable.cat6};
    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        ImageView img = item.findViewById(R.id.img);
        img.setImageResource(imgs[position]);
        return item;
    }
}
