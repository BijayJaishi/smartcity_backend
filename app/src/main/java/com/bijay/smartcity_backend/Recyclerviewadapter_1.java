package com.bijay.smartcity_backend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bijay on 7/24/2018.
 */

public class Recyclerviewadapter_1 extends RecyclerView.Adapter<Recyclerviewadapter_1.MyViewHolder>{

    Context context;
    List<Modeladdcate>modeladdcates;

    public Recyclerviewadapter_1(categories categories, List<Modeladdcate> modeladdcateList) {
        this.context = context;
        this.modeladdcates = modeladdcates;
    }

    @NonNull
    @Override
    public Recyclerviewadapter_1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reclycer_view_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recyclerviewadapter_1.MyViewHolder holder, int position) {


        final Modeladdcate modeladdcate = modeladdcates.get(position);

        //// getting the item name and id and displaying in txt_item_price

        holder.txt_item_desc.setText(modeladdcate.getId());


        holder.txt_item_name.setText(modeladdcate.getName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

     class MyViewHolder extends RecyclerView.ViewHolder {

         TextView txt_item_name,txt_item_desc;

         public MyViewHolder(View itemView) {
             super(itemView);

             txt_item_desc = itemView.findViewById(R.id.item_desc);
             txt_item_name = itemView.findViewById(R.id.item_name);
         }
     }
}
