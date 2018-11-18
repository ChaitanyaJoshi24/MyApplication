package com.chetz.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chetz.myapplication.R;
import com.chetz.myapplication.models.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    Context context;
    ArrayList<Product> productArrayList;
    OnClickListner onClickListner;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList, OnClickListner onClickListner) {
        this.onClickListner = onClickListner;
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_prod,null,false);
        return new ProductViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, final int i) {

        productViewHolder.tvProductname.setText(productArrayList.get(i).getName());
        productViewHolder.tvDescription.setText(productArrayList.get(i).getDescription());
        productViewHolder.tvWeight.setText(productArrayList.get(i).getWeight());
        productViewHolder.tvPrice.setText("$" +productArrayList.get(i).getPrice());
        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListner != null){
                    onClickListner.onItemClick(productArrayList.get(i));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView tvProductname;
        TextView tvDescription;
        TextView tvWeight;
        TextView tvPrice;



        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProductname = (TextView) itemView.findViewById(R.id.txt_productname);
            tvDescription = (TextView) itemView.findViewById(R.id.txt_description);
            tvWeight = (TextView) itemView.findViewById(R.id.txt_weight);
            tvPrice = (TextView) itemView.findViewById(R.id.txt_price);
        }
    }

    public interface OnClickListner{

        public void onItemClick(Product product);


    }
}
