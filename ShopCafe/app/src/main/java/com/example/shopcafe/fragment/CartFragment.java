package com.example.shopcafe.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcafe.Home;
import com.example.shopcafe.R;
import com.example.shopcafe.adapter.ProductCartAdapter;
import com.example.shopcafe.db.SQLiteHelper;
import com.example.shopcafe.model.Bill;
import com.example.shopcafe.model.Order;
import com.example.shopcafe.model.Product;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


public class CartFragment extends Fragment {
    private int totalPrice;
    private int countProduct;
    private View mView;
    private Home home;
    private DecimalFormat format;

    //    2 layout để hiện thị 2 trạng thái cảu giỏ hàng
    private RelativeLayout rlCartEmpty,rlCart;

    private List<Product> listCartProduct;
    private RecyclerView rcvCartProduct;
    private TextView tvCartTotalPrice;
    private EditText edtCartCustName,edtCartCustAddress,edtCartCustPhone;
    private Button btnCartOrder;

    private ProductCartAdapter productCartAdapter;

    public CartFragment() {
    }

    public CartFragment(List<Product> listCartProduct) {
        this.listCartProduct = new ArrayList<>();
        this.listCartProduct = listCartProduct;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_cart,container,false);
        // Khởi tạo các item
        initItem();

        // Set hiển thị các view
        setVisibilityView();
        return mView;
    }
//
//    // Khởi tạo các item
    private void initItem(){
        productCartAdapter = new ProductCartAdapter();
        rlCartEmpty = mView.findViewById(R.id.rl_cart_empty);
        rlCart = mView.findViewById(R.id.rl_cart);
        rcvCartProduct = mView.findViewById(R.id.rcv_cart_product);
        tvCartTotalPrice = mView.findViewById(R.id.tv_cart_total_price);
        edtCartCustName = mView.findViewById(R.id.edt_cart_cust_name);
        edtCartCustAddress = mView.findViewById(R.id.edt_cart_cust_address);
        edtCartCustPhone = mView.findViewById(R.id.edt_cart_cust_phone);
        btnCartOrder = mView.findViewById(R.id.btn_cart_order);
        btnCartOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order(edtCartCustAddress.getText().toString(), edtCartCustName.getText().toString(),
                        edtCartCustPhone.getText().toString(), getCurrentDate(), "chua thanh toan", getTotalItem(), getTotalPrice(), listCartProduct);
                // Thêm dữ liệu
//                Order order = new Order();
                String uniqueKey = System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
                order.setOrderNo(uniqueKey);
                Bill bill = new Bill();
                bill.setId(order.getOrderNo());
                bill.setName(order.getCustName());
                bill.setPhone(order.getCustPhone());
                bill.setDateOrder(order.getDateOrder());
                String tmp ="";
                for (Product product : order.getListProductCart()){
                    tmp += product.getProductName()+" "+product.getNumProduct()+" sản phẩm\n";
                }
                bill.setBillProduct(tmp);
                bill.setAddress(order.getCustAddress());
                bill.setTotalPrices(order.getTotalPrice());
                SQLiteHelper db = new SQLiteHelper(getActivity());
                db.addBill(bill);
                home.toOrderInfoFragment(order);
//                home.toHistoryFragment();
                listCartProduct.clear();
                Log.i("thanhcong","hehe");
                Log.i("thanhcong2",String.valueOf(db.getAllBill().size()));
            }
        });

        home = (Home) getActivity();
        format = new DecimalFormat("###,###,###");
    }
    private String getCurrentDate() {
        // Tạo một đối tượng Calendar để lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        // Lấy ngày hiện tại từ đối tượng Calendar
        Date currentDate = calendar.getTime();
        // Định dạng ngày theo "dd-MM-yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        // Trả về ngày dưới dạng chuỗi
        return dateFormat.format(currentDate);
    }
    // Set trạng thái hiển thị các view
    private void setVisibilityView(){
        if (listCartProduct.size() == 0){

            // Hiển thị giỏ hàng rỗng
            setVisibilityEmptyCart();
        }else {

            // Hiển thị giỏ hàng
            setVisibilityCart();
            setDataProductCartAdapter();
        }
    }
    // set data ProductCartAdapter
    private void setDataProductCartAdapter(){
        productCartAdapter.setData(listCartProduct,home,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(home,RecyclerView.VERTICAL,false);
        rcvCartProduct.setLayoutManager(linearLayoutManager);
        rcvCartProduct.setAdapter(productCartAdapter);
    }

    // Hiển thị giỏ hàng
    private void setVisibilityCart(){
        rlCartEmpty.setVisibility(View.GONE);
        rlCart.setVisibility(View.VISIBLE);
        String total = format.format(getTotalPrice());
        tvCartTotalPrice.setText( total +" VNĐ" );
    }

    // Hiển thị giỏ hàng rỗng
    public void setVisibilityEmptyCart(){
        rlCartEmpty.setVisibility(View.VISIBLE);
        rlCart.setVisibility(View.GONE);
    }


    // lấy giá trị tổng tiền tất cả sản phẩm trong giỏ hàng
    private int getTotalPrice(){
        totalPrice = 0;
        for (Product product : listCartProduct){
            int priceProduct = (int) product.getProductPrice() ;
            totalPrice = totalPrice +  priceProduct * product.getNumProduct();
        }
        return totalPrice;
    }
    private int getTotalItem(){
        for (Product product : listCartProduct){
            countProduct = countProduct + product.getNumProduct() ;
        }
        return countProduct;
    }
    // Set giá trị hiển thị tổng tiền khi tăng giảm số lượng
    // mode = 0 : giảm
    // mode = 1 : tăng
    public void setTotalPrice(int mode,int count, int priceProduct ){
        if( mode == 0){
            totalPrice = totalPrice - priceProduct * count;
        }else if (mode == 1){
            totalPrice = totalPrice + priceProduct * count;
        }

        tvCartTotalPrice.setText( format.format(totalPrice) + " VNĐ");
    }

    // Set sô lượng sản phẩm sau nhấn nút tăng giảm
    public void setCountForProduct(int possion,int countProduct){
        listCartProduct.get(possion).setNumProduct(countProduct);
    }
}
