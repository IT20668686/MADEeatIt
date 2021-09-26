package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ui.discount.DiscountFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    DiscountFragment context;
    ArrayList<DiscountHelperClass> list;

    public MyAdapter(DiscountFragment context, ArrayList<DiscountHelperClass> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @NotNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context.getActivity()).inflate(R.layout.item,parent,false);
       return  new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
     DiscountHelperClass helper =list.get(position);
     holder.name.setText(helper.name);
     holder.amount.setText(helper.amount);
     holder.type.setText(helper.type);
     holder.date.setText(helper.date);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,amount,date,type;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvname);
            amount=itemView.findViewById(R.id.tvamount);
            type=itemView.findViewById(R.id.tvtype);
            date=itemView.findViewById(R.id.tvdate);
        }
    }
}
