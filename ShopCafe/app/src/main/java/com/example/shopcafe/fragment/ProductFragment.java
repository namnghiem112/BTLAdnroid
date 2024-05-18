package com.example.shopcafe.fragment;

import static com.example.shopcafe.Home.myViewPagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.shopcafe.Home;
import com.example.shopcafe.R;
import com.example.shopcafe.adapter.MyViewPagerAdapter;
import com.example.shopcafe.adapter.ProductAdapter;
import com.example.shopcafe.db.SQLiteHelper;
import com.example.shopcafe.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ProductFragment extends Fragment {

    private Home home;
    private Timer mTimer;
    private List<Product> listAllProduct;

    private View mView;
    private RecyclerView rcvProduct;

    private AutoCompleteTextView atcProductSearch;

    private ProductAdapter productAdapter;

    public ProductFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_product, container, false);
        // Khởi tạo các item
        initItem();

        // Set Adapter cho rcvProduct
        setDataProductAdapter();

        return mView;
    }

    // Khởi tạo các item
    private void initItem() {
        rcvProduct = mView.findViewById(R.id.rcv_product);
        atcProductSearch = mView.findViewById(R.id.atc_product_search);
        listAllProduct = getDataProduct();
//        productAdapter = new ProductAdapter(listAllProduct);
//        Log.i("size",String.valueOf(listAllProduct.size()));
//        Log.i("pro1",listAllProduct.get(0).toString());
        home = (Home) getActivity();
    }

    // Set Adapter cho rcvProduct
    private void setDataProductAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(home, 2);
        rcvProduct.setLayoutManager(gridLayoutManager);
        productAdapter = new ProductAdapter(getDataProduct());
        atcProductSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("A", "change");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().isEmpty()) {
//                    getDataProduct();
                    productAdapter.setmListProduct(getDataProduct());
                } else {
                    Log.v("search",searchData(charSequence.toString()).toString());
                    productAdapter.setmListProduct(searchData(charSequence.toString()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("C", "change");
            }
        });
        rcvProduct.setAdapter(productAdapter);
        // lấy interface
        productAdapter.setiClickItemProductListener(new ProductAdapter.IClickItemProductListener() {
            @Override
            public void onClickItemProduct(Product product) {
                home.toDetailProductFragment(product);
//                Log.i("xuat","2");
//                myViewPagerAdapter.replaceFragment(0,new DetailProductFragment());
//                Log.v("sizefragmen",String.valueOf(myViewPagerAdapter.getItemCount()));
//                startActivity(new Intent(getActivity(),Home.class));
            }
        });
    }
    private List<Product> getDataProduct() {
        List<Product> mListProduct = new ArrayList<>();
        SQLiteHelper db = new SQLiteHelper(getActivity());
//        createData();
        mListProduct = db.getAllProduct();
        return mListProduct;
    }

    private void createData() {
        SQLiteHelper db = new SQLiteHelper(getActivity());
        db.addProduct(new Product(1,"https://product.hstatic.net/1000075078/product/1697442235_cloudfee-hanh-nhan-nuong_ba2462626af44d86acc19997e654a0c3_large.jpg","Clound hanh nhan nuong","Rất ngon phù hợp với mọi lứa tuổi","The Coffee house",49000,1));
        db.addProduct(new Product(2,"https://product.hstatic.net/1000075078/product/1675355354_bg-tch-sua-da-no_4fbf208885ed464ab4b5e145336d42a2_large.jpg","The Coffee house sua da","Rất ngon phù hợp với mọi lứa tuổi","The Coffee house",59000,1));
        db.addProduct(new Product(3,"https://product.hstatic.net/1000075078/product/1669736893_hi-tea-vai_5b6d0f757f2c44328f84fcb5fb972256_large.png","Hi-Tea Vai","Rất ngon phù hợp với mọi lứa tuổi","The Coffee house",69000,1));
        db.addProduct(new Product(4,"https://product.hstatic.net/1000075078/product/1669736835_ca-phe-sua-da_e6168b6a38ec45d2b4854d2708b5d542_large.png","Ca phe sua da","Rất ngon phù hợp với mọi lứa tuổi","The Coffee house",99000,1));
        db.addProduct(new Product(5,"https://product.hstatic.net/1000075078/product/1697446642_ca-phe-den-da-tui_a178a9f2d9a84425b5c5397da639bf92_large.jpg","Ca phe sua da den tui","Rất ngon phù hợp với mọi lứa tuổi","The Coffee house",109000,1));
        db.addProduct(new Product(6,"https://product.hstatic.net/1000075078/product/cpsd-3in1_971575_21adaf23d2854493afe6c4941308079d_large.jpg","Ca phe sua da hoa tan","Rất ngon phù hợp với mọi lứa tuổi","The Coffee house",99000,1));
        db.addProduct(new Product(7,"https://product.hstatic.net/1000075078/product/1639648313_ca-phe-sua-da-hoa-tan-dam-vi-viet_cae2bfaf82ca499597e1b2f5b947b4b3_large.jpg","Ca phe hoa tan dam vi","Rất ngon phù hợp với mọi lứa tuổi","The Coffee house",69000,1));
        db.addProduct(new Product(8,"https://product.hstatic.net/1000075078/product/p6-lon-3in1_717216_99bfb5b1366f4fe6bc2383d697102d26_large.jpg","Ca phe sua da pack 6 lon","Rất ngon phù hợp với mọi lứa tuổi","The Coffee house",79000,1));
        db.addProduct(new Product(9,"https://product.hstatic.net/1000075078/product/24-lon-cpsd_225680_272d2572b0a94256b7e9f81cf07ae299_large.jpg","Thung 24 lon ca phe sua da","Rất ngon phù hợp với mọi lứa tuổi","The Coffee house",199000,1));
        db.addProduct(new Product(10,"https://product.hstatic.net/1000075078/product/1697442235_cloudfee-hanh-nhan-nuong_ba2462626af44d86acc19997e654a0c3_large.jpg","Clound hạnh nhân nướng","Rất ngon phù hợp với mọi lứa tuổi","The Coffee house",249000,1));
    }

    private List<Product> searchData(String name) {
        List<Product> mListProduct = new ArrayList<>();
        SQLiteHelper db = new SQLiteHelper(getActivity());
        List<Product> listProducts = db.getAllProduct();
        for (Product product : listProducts){
            if(product.getProductName().toLowerCase().contains(name)){
                mListProduct.add(product);
            }
        }
        return mListProduct;
    }
}
