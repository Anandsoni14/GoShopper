package in.co.goshopper.android;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anand Soni on 24-05-2016.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>

    {
         List<ProductDetails> productList;


        public static class MyViewHolder extends RecyclerView.ViewHolder {
             TextView ProductName, shop , Price, dist ;
            CardView cardview;
            ImageView ProductImg;

            public MyViewHolder(View view) {
                super(view);
                cardview =  (CardView) view.findViewById(R.id.cv);
                ProductName = (TextView) view.findViewById(R.id.productName);
                Price = (TextView) view.findViewById(R.id.price);
                shop = (TextView) view.findViewById(R.id.shop);
                dist=(TextView) view.findViewById(R.id.dist);
                ProductImg = (ImageView) view.findViewById(R.id.productImg);
            }
        }

     ProductsAdapter(List < ProductDetails > productList) {
    this.productList = productList;
       }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder,int position){
        ProductDetails ProductDetails = productList.get(position);
        holder.ProductName.setText(ProductDetails.getProductName());
        holder.Price.setText(ProductDetails.getPrice());
        holder.shop.setText(ProductDetails.getshop());
    }

    @Override
    public int getItemCount () {
        return productList.size();
    }
}