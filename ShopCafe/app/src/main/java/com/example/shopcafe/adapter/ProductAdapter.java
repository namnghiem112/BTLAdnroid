package com.example.shopcafe.adapter;

import android.content.Intent;
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
import com.example.shopcafe.fragment.DetailProductFragment;
import com.example.shopcafe.model.Product;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHoder> {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    private List<Product> mListProduct;
    private Home home;

    // Khai báo Interface giúp cho việc click vào phần tử của recycleview
    public interface IClickItemProductListener{
        void onClickItemProduct(Product product);
    }

    public void setiClickItemProductListener(IClickItemProductListener iClickItemProductListener) {
        this.iClickItemProductListener = iClickItemProductListener;
    }

    private  IClickItemProductListener iClickItemProductListener;
    public ProductAdapter(List<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }

    public DecimalFormat getFormatPrice() {
        return formatPrice;
    }

    public void setFormatPrice(DecimalFormat formatPrice) {
        this.formatPrice = formatPrice;
    }

    public List<Product> getmListProduct() {
        return mListProduct;
    }

    public void setmListProduct(List<Product> mListProduct) {
        this.mListProduct = mListProduct;
        notifyDataSetChanged();
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    //    set dữ liệu vào recycle view
    public void setData(List<Product> mList, Home home) {
        this.mListProduct = mList;
        this.home = home;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHoder holder, int position) {
        Product product = mListProduct.get(position);
        if(product == null){
            return;
        }
        else{
            Glide.with(holder.itemView.getContext())
                    .load(product.getUrlImg())
                    .placeholder(R.drawable.placeholder_image) // Hình ảnh mặc định
                    .error(R.drawable.error_image) // Hình ảnh lỗi
                    .into(holder.imgPhotoProduct);
            holder.tvProductName.setText(product.getProductName());
            holder.tvProductPrice.setText(formatPrice.format(product.getProductPrice()) + " VNĐ");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iClickItemProductListener.onClickItemProduct(product);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if(mListProduct != null){
            return mListProduct.size();
        }else return 0;
    }

    public class ProductViewHoder extends RecyclerView.ViewHolder{

        ImageView imgPhotoProduct;
        TextView tvProductName,tvProductPrice;
        IClickItemProductListener iClickItemProductListener;

        public ProductViewHoder(@NonNull View itemView) {
            super(itemView);
            imgPhotoProduct = itemView.findViewById(R.id.img_photo_product);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
        }
    }
}
