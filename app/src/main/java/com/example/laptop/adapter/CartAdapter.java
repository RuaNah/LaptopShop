package com.example.laptop.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.laptop.IImageClickListener;
import com.example.laptop.R;
import com.example.laptop.model.Cart;
import com.example.laptop.model.event.EventSumTotal;
import com.example.laptop.ultils.Ultils;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    List<Cart> cartList;

    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.name.setText(cart.getName());
        Glide.with(context).load(cart.getImage()).into(holder.img);
        holder.quantity.setText(cart.getQuantity()+ "");
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.price.setText(decimalFormat.format(cart.getPrice()));
        int total = cart.getPrice() * cart.getQuantity();
        holder.total.setText(decimalFormat.format(total));
        holder.setiImageClickListener(new IImageClickListener() {
            @Override
            public void onImageClick(View view, int pos, int value) {
                if (value == 1){
                    if (cartList.get(pos).getQuantity()>1){// neu giam ma so luong lon hon 1
                        int slnew = cartList.get(pos).getQuantity()-1;
                        cartList.get(pos).setQuantity(slnew);

                        holder.quantity.setText(cartList.get(pos).getQuantity()+ "");
                        int total = cartList.get(pos).getPrice() * cartList.get(pos).getQuantity();
                        holder.total.setText(decimalFormat.format(total));
                        EventBus.getDefault().postSticky(new EventSumTotal());
                    }
                    else if( cartList.get(pos).getQuantity() == 1){//neu giam ma so luong = 1
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn có muốn xóa sản phẩm khỏi giỏ hàng?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Ultils.listCart.remove(pos);
                                notifyDataSetChanged();
                                EventBus.getDefault().postSticky(new EventSumTotal());
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }
                }
                else if (value == 2){ // them
                    if (cartList.get(pos).getQuantity() < 11){
                        int slnew = cartList.get(pos).getQuantity()+1;
                        cartList.get(pos).setQuantity(slnew);
                    }
                    holder.quantity.setText(cartList.get(pos).getQuantity()+ "");
                    int total = cartList.get(pos).getPrice() * cartList.get(pos).getQuantity();
                    holder.total.setText(decimalFormat.format(total));
                    EventBus.getDefault().postSticky(new EventSumTotal());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img,imgSub,imgAdd;
        TextView name,price,quantity,total;
        IImageClickListener iImageClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_cart);
            name = itemView.findViewById(R.id.cart_name);
            price = itemView.findViewById(R.id.cart_price);
            quantity = itemView.findViewById(R.id.cart_quantity);
            total  = itemView.findViewById(R.id.cart_total);
            imgSub = itemView.findViewById(R.id.cart_sub);
            imgAdd = itemView.findViewById(R.id.cart_add);

            imgAdd.setOnClickListener(this);
            imgSub.setOnClickListener(this);
        }

        public void setiImageClickListener(IImageClickListener iImageClickListener) {
            this.iImageClickListener = iImageClickListener;
        }

        @Override
        public void onClick(View view) {
            if (view  == imgSub){
                iImageClickListener.onImageClick(view,getAdapterPosition(),1);
            }else if(view == imgAdd){
                iImageClickListener.onImageClick(view,getAdapterPosition(),2);
            }
        }
    }
}
