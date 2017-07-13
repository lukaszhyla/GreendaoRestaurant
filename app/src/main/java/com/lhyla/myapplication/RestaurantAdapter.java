package com.lhyla.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lhyla.myapplication.db.Product;

import java.util.List;

/**
 * Created by RENT on 2017-07-13.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {
    private List<Product> productList;
    Context context;

    public RestaurantAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public RestaurantAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.activity_main_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RestaurantAdapter.MyViewHolder holder, int position) {
        final Product item = productList.get(position);

        holder.productName.setText(item.getName());
        holder.productPrice.setText(String.valueOf(item.getPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.row_name_text_view);
            productPrice = (TextView) itemView.findViewById(R.id.row_price_text_view);
        }
    }
}
