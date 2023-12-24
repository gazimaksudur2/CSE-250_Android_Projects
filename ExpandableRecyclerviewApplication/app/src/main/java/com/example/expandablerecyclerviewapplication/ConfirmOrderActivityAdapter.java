package com.example.expandablerecyclerviewapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConfirmOrderActivityAdapter extends RecyclerView.Adapter<ConfirmOrderActivityAdapter.ConfirmOrderViewHolder> {

    private ArrayList<OrderListModel> orderListModels;
    Activity activity;
    public ConfirmOrderActivityAdapter(Activity activity){
        this.activity = activity;
        orderListModels = ((UpdateSelectedItem) ApplicationMain.getMyContext()).getItems();
    }
    public class ConfirmOrderViewHolder extends RecyclerView.ViewHolder{

        TextView name,price;
        public ConfirmOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);

        }
    }
    @NonNull
    @Override
    public ConfirmOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items,parent,false);
        ConfirmOrderViewHolder confirmOrderViewHolder = new ConfirmOrderViewHolder(view);
        return confirmOrderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmOrderViewHolder holder, int position) {
        OrderListModel currentitem = orderListModels.get(position);
        holder.name.setText(currentitem.name());
    }

    @Override
    public int getItemCount() {
        return orderListModels.size();
    }

}
