package com.example.dbwithlandr;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabItem;

import java.util.ArrayList;

public class Recycleradapter extends RecyclerView.Adapter
        <Recycleradapter.ViewHolder> {


    Context context;
    ArrayList<datamodel> arraymodel;
    Recycleradapter (Context context, ArrayList<datamodel> arraymodel){
        this.context = context;
        this.arraymodel = arraymodel;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rcname);
            email = itemView.findViewById(R.id.rcemail);
        }
    }

    //oncreate view method
    @NonNull
    @Override
    public Recycleradapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //view generate, from is method, path define, group, use false to scroll
      View v =  LayoutInflater.from(context).inflate(R.layout.content_home,
              parent,false);

      ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycleradapter.ViewHolder holder, int position) {
    holder.name.setText(arraymodel.get(position).firstname);
    holder.email.setText(arraymodel.get(position).dbmemail);
    }

    @Override
    public int getItemCount() {
        return arraymodel.size();
    }


}
