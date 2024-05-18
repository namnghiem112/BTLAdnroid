package com.example.shopcafe.fragment;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.shopcafe.Home;
import com.example.shopcafe.R;
import com.example.shopcafe.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailProductFragment extends Fragment {
    private DecimalFormat format = new DecimalFormat("###,###,###");

    // Kiểm tra Product đã được thêm vào cart chưa
    private Boolean isAddToCart;

    // Activity
    private Home home;

    // View
    private View mView;
    private Product detailProduct;
    private List<Product> listCartProduct;
    private TextView tvDetailProductName,tvDetailProductPrice,tvDetailProductDescription;
    private Button btnDetailProductBuy;
    private ImageView imgDetailProductPhoto;

    public DetailProductFragment() {
    }

    public DetailProductFragment(Product detailProduct) {
        this.detailProduct = detailProduct;
    }

    public DetailProductFragment(Product product, List<Product> listProduct) {
        detailProduct = product;
        listCartProduct = listProduct;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_detail_product, container, false);
        initItem();
        setValueItem();
        return  mView;
    }
    // Khởi tạo các item
    private void initItem(){
        home = (Home) getActivity();
        tvDetailProductName = mView.findViewById(R.id.tv_detail_product_name);
        tvDetailProductPrice = mView.findViewById(R.id.tv_detail_product_price);
        tvDetailProductDescription = mView.findViewById(R.id.tv_detail_product_description);
        imgDetailProductPhoto = mView.findViewById(R.id.img_detail_product_photo);
        btnDetailProductBuy = mView.findViewById(R.id.btn_detail_product_buy);

        if(listCartProduct == null){
            listCartProduct = new ArrayList<>();
        }
    }

    private void setValueItem(){
        if(detailProduct != null){
            tvDetailProductName.setText(detailProduct.getProductName());
            tvDetailProductPrice.setText(format.format(detailProduct.getProductPrice() ) + " VNĐ");
            Glide.with(getContext()).load(detailProduct.getUrlImg()).into(imgDetailProductPhoto);
            tvDetailProductDescription.setText(detailProduct.getDescription());
            //btn mua
            btnDetailProductBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnDetailProductBuy.setText("Đã Mua");
                    btnDetailProductBuy.setBackgroundResource(R.drawable.custom_button_gray);
                    home.addToListCartProdct(detailProduct);
                    Toast.makeText(getActivity(),"Đã thêm sản phẩm vào giỏ hàng",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
