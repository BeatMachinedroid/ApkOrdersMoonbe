package com.example.moonbecafe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moonbecafe.MenuCategory;
import com.example.moonbecafe.Model.Category.DataCategory;
import com.example.moonbecafe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {
    private final List<DataCategory> categories;
    private Context context;

    public AdapterCategory(List<DataCategory> categories, Context context){
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View hasil = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewdashcate, parent, false);
        return new ViewHolder(hasil);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataCategory dataCategory = categories.get(position);
        holder.name.setText(dataCategory.getName());
        holder.imgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MenuCategory.class).
                        putExtra("id", dataCategory.getIdcate())
                        .putExtra("cate",dataCategory.getName())
                );
            }
        });
        Picasso.get()
                .load("http://192.168.62.149/MoonBeCafe-master/storage/app/public/category/"+dataCategory.getImage())
                .resize(200 , 200)
                .into(holder.imgs);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgs;
        TextView name;

        public ViewHolder(@NonNull View hasil) {
            super(hasil);
            imgs = hasil.findViewById(R.id.gambarcate);
            name = hasil.findViewById(R.id.titlecate);
        }
    }
}
