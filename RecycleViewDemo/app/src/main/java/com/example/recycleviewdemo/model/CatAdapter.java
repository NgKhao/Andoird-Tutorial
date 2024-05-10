package com.example.recycleviewdemo.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleviewdemo.R;

import java.util.List;

//trước tiên xử lý viewHolder sau đó mới kế thừa viewholder vào class CatAdapter
public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private List<Cat> mList;
//    private Context context;

    private CatItemListener catItemListener;

    public CatAdapter(List<Cat> mList) {
        this.mList = mList;
//        this.context = context;
    }

    public void setCatItemListener(CatItemListener catItemListener) {
        this.catItemListener = catItemListener;
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
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, cat.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(mList != null)
            return mList.size();
        return 0;
    }

    //    Holder là từng thành phần(compo...) của recycleview
    public class CatViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private ImageView img;
        private TextView tv;
        private CardView cardView;
        public CatViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tv = view.findViewById(R.id.tname);
            view.setOnClickListener(this);
//            cardView = view.findViewById(R.id.cview);
        }

        @Override
        public void onClick(View v) {
            if(catItemListener != null){
                catItemListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface CatItemListener {
        public void onItemClick(View view, int postion);

    }
}
