package com.example.laptop.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.laptop.R;
import com.example.laptop.model.Category;

import java.util.List;

public class CategoriesAdapter extends BaseAdapter {
    List<Category> listCategory;
    Context context;

    public CategoriesAdapter( Context context,List<Category> listCategory) {
        this.listCategory = listCategory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCategory.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        TextView nameCate;
        ImageView imageCate;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_category,null);
            viewHolder.nameCate = view.findViewById(R.id.item_namecate);
            viewHolder.imageCate = view.findViewById(R.id.item_imagecate);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nameCate.setText(listCategory.get(i).getName());
        Glide.with(context).load(listCategory.get(i).getImage()).into(viewHolder.imageCate);

        return view;
    }
}
