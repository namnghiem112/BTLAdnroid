package com.example.shopcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.shopcafe.adapter.MyViewPagerAdapter;
import com.example.shopcafe.db.SQLiteHelper;
import com.example.shopcafe.fragment.CartFragment;
import com.example.shopcafe.fragment.DetailProductFragment;
import com.example.shopcafe.fragment.HistoryFragment;
import com.example.shopcafe.fragment.OrderInfoFragment;
import com.example.shopcafe.fragment.ProductFragment;
import com.example.shopcafe.fragment.UserFragment;
import com.example.shopcafe.model.Order;
import com.example.shopcafe.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
    public static MyViewPagerAdapter myViewPagerAdapter;
    private FragmentTransaction fragmentTransaction;
    public static String HOME_USER_NAME ="";
    private List<Product> listCartProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HOME_USER_NAME = getIntent().getStringExtra("email");
        listCartProduct = new ArrayList<>();
        // Khởi tạo các item
        initItem();
        // Set data cho ahBotNavHome
        setDataBotNavHome();
        // xóa tat ca bills
//        SQLiteHelper dbHelper = new SQLiteHelper(getApplicationContext());
//        dbHelper.deleteAllRowsFromTable("bills");
    }
    private void initItem() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        if(listCartProduct == null){
//            listCartProduct = new ArrayList<>();
//        }
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contet_frame, new ProductFragment());
        fragmentTransaction.commit();
    }
    private void setDataBotNavHome() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_item_list){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contet_frame, new ProductFragment());
                    fragmentTransaction.commit();
                } else if (item.getItemId() == R.id.menu_item_info){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contet_frame, new CartFragment(listCartProduct));
                    fragmentTransaction.commit();
                } else if (item.getItemId() == R.id.menu_item_search){
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contet_frame, new HistoryFragment());
                    fragmentTransaction.commit();
                } else {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.contet_frame, new UserFragment());
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }
    public void toDetailProductFragment(Product product) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contet_frame, new DetailProductFragment(product));
        fragmentTransaction.commit();
    }
    public void toOrderInfoFragment(Order order) {
        Log.i("kiemtraobjethome",order.toString());
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contet_frame, new OrderInfoFragment(order));
        fragmentTransaction.commit();
    }
    public void toHistoryFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contet_frame, new HistoryFragment());
        fragmentTransaction.commit();
    }
    // Thêm sản phẩm đã chọn vào giỏ hàng
    public void addToListCartProdct(Product product) {
        boolean kiemtra = false;
        for(Product product1 : listCartProduct){
            if(product1.getId()==product.getId()){
                product1.setNumProduct(product1.getNumProduct()+1);
                kiemtra = true;
                break;
            }
        }
        if(kiemtra==false) listCartProduct.add(product);
    }
    public void setCountForProduct(int possion, int countProduct) {
        listCartProduct.get(possion).setNumProduct(countProduct);
    }


}