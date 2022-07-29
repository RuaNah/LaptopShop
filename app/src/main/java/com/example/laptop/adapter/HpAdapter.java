package com.example.laptop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laptop.ItemClickListener;
import com.example.laptop.R;
import com.example.laptop.activity.DetailActivity;
import com.example.laptop.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class HpAdapter extends RecyclerView.Adapter<HpAdapter.MyViewHolder> {
    Context context;
    List<Product> listHp;

    public HpAdapter(Context context, List<Product> listHp) {
        this.context = context;
        this.listHp = listHp;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asus,parent,false);
        return new HpAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = listHp.get(position);
        holder.txtName.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPrice.setText(decimalFormat.format(product.getPrice()) + " VNĐ");
        holder.txtDescription.setText(product.getDescription());
        Glide.with(context).load(product.getImage()).into(holder.img);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if (!isLongClick){
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("detail",product);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHp.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtName,txtPrice,txtDescription;
        ImageView img;
        private ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name_asus);
            txtPrice = itemView.findViewById(R.id.price_asus);
            txtDescription = itemView.findViewById(R.id.description_asus);
            img = itemView.findViewById(R.id.image_Asus);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }
}
