package com.example.rycycleviewdemo_crud.model;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rycycleviewdemo_crud.R;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends  RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private Context context;
    private List<Cat> mList;
    private List<Cat> listBackup; // list giữ lại giá trị gốc sau khi search
    private CatItemListener catItemListener;


    public CatAdapter(Context context) {
        this.context = context;
        this.mList = new ArrayList<>();
        this.listBackup = new ArrayList<>();
    }

    public List<Cat> getListBackup() {
        return listBackup;
    }

    public void filterList(List<Cat> filterlist){
        mList = filterlist;
        notifyDataSetChanged();
    }

    public void setClickItemListener(CatItemListener catItemListener){
        this.catItemListener = catItemListener;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new CatViewHolder(view);
    }

//    lấy ra Cat tại vị trí được chọn
    public Cat getItem(int positon){
        return mList.get(positon);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mList.get(position);
        if(cat == null)
            return;
        holder.img.setImageResource(cat.getImg());
        holder.tvName.setText(cat.getName());
        holder.tvDescribe.setText(cat.getDescribe());
        holder.tvPrice.setText(cat.getPrice() + "");
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tạo một hộp thoại thông báo
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo xóa");
                builder.setMessage("Bạn có chắc chán muốn xóa " + holder.tvName.getText() + " này không?");
                builder.setIcon(R.drawable.exit_icon);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listBackup.remove(holder.getAbsoluteAdapterPosition());
                        mList.remove(holder.getAbsoluteAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mList != null)
            return mList.size();
        return 0;
    }


//    thêm method add để thêm obj vào adapter
    public  void add(Cat c){
        listBackup.add(c);
        mList.add(c);
        notifyDataSetChanged();// thông báo cho RecycleView để cập nhập giao diện
//        nếu không có thì chỉ dữ liệu đổi chứ UI không đổi
    }

    public void update(int position, Cat cat){
        listBackup.set(position, cat);
        mList.set(position, cat);
        notifyDataSetChanged();
    }
    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName, tvDescribe, tvPrice;
        private Button btRemove;
        public CatViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tvName = view.findViewById(R.id.txtName);
            tvDescribe = view.findViewById(R.id.txtDescribe);
            tvPrice = view.findViewById(R.id.txtPrice);
            btRemove = view.findViewById(R.id.btRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(catItemListener != null)
                catItemListener.onItemClick(v, getAbsoluteAdapterPosition());
        }
    }

    public interface CatItemListener{
        void onItemClick(View view, int position);
    }
}
