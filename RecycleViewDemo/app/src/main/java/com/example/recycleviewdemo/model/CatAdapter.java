package com.example.recycleviewdemo.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleviewdemo.R;

import java.util.List;

//trước tiên xử lý viewHolder sau đó mới kế thừa viewholder vào class CatAdapter
public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private List<Cat> mList;

    public CatAdapter(List<Cat> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
//    method này return view
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        return new CatViewHolder(view);
    }

//    inflater(thổi) data cho một item
    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mList.get(position);
        if(cat == null)
            return;
        holder.img.setImageResource(cat.getImage());
        holder.tv.setText(cat.getName());
    }

    @Override
    public int getItemCount() {
        if(mList != null)
            return mList.size();
        return 0;
    }

    //    Holder là từng thành phần(compo...) của recycleview
    public class CatViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tv;
        public CatViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tv = view.findViewById(R.id.tname);
        }
    }
}
