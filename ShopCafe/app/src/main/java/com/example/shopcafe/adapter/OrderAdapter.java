package com.example.shopcafe.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopcafe.Home;
import com.example.shopcafe.R;
import com.example.shopcafe.fragment.CartFragment;
import com.example.shopcafe.fragment.OrderInfoFragment;
import com.example.shopcafe.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ProductCartViewHolder>{
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Product> listProductCart;

    public OrderAdapter(List<Product> listProductCart) {
        this.listProductCart = listProductCart;
    }

    public void setListProductCart(List<Product> listProductCart) {
        this.listProductCart = listProductCart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        return new ProductCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = listProductCart.get(position);
        if(product == null){
            return;
        }
        else{
            Glide.with(holder.imgPhotoCart.getContext()).load(product.getUrlImg()).into(holder.imgPhotoCart);
            holder.tvNameProductCart.setText(product.getProductName());
            holder.tvPriceProductCart.setText(formatPrice.format(product.getProductPrice()) + " VND");
            int count = product.getNumProduct();
            if(count != 0){
                holder.tvCountCart.setText(String.valueOf(count));
            }else {
                holder.tvCountCart.setText(String.valueOf(1));
            }
        }
    }

    @Override
    public int getItemCount() {
        if(listProductCart != null){
            return listProductCart.size();
        }
        return 0;
    }

    public class ProductCartViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhotoCart;
        TextView tvNameProductCart, tvPriceProductCart,tvCountCart ;
        ImageView imgMinusCart,imgPlusCart,imgRemoveCart;

        public ProductCartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhotoCart = itemView.findViewById(R.id.img_photo_cart);
            tvNameProductCart = itemView.findViewById(R.id.tv_name_product_cart);
            tvPriceProductCart = itemView.findViewById(R.id.tv_price_product_cart);
            imgMinusCart = itemView.findViewById(R.id.img_minus_cart);
            tvCountCart = itemView.findViewById(R.id.tv_count_cart);
            imgPlusCart = itemView.findViewById(R.id.img_plus_cart);
            imgRemoveCart = itemView.findViewById(R.id.img_remove_cart);
        }
    }
}
