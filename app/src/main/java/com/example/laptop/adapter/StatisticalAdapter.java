package com.example.laptop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laptop.R;
import com.example.laptop.model.Order;

import java.text.DecimalFormat;
import java.util.List;

public class StatisticalAdapter extends RecyclerView.Adapter<StatisticalAdapter.MyViewHolder> {
    Context context;
    List<Order> cartList;

    public StatisticalAdapter(Context context, List<Order> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistical,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Order order = cartList.get(position);
        holder.name.setText(order.getCustomnername());
        holder.date.setText(order.getDateorder());
        holder.price.setText((order.getTotalprice()));
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,date,price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.statis_name);
            date = itemView.findViewById(R.id.statis_date);
            price = itemView.findViewById(R.id.statis_total);
        }
    }
}
