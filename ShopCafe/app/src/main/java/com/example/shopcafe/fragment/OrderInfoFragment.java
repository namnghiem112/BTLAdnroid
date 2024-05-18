package com.example.shopcafe.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcafe.Home;
import com.example.shopcafe.R;
import com.example.shopcafe.adapter.OrderAdapter;
import com.example.shopcafe.model.Order;
import com.example.shopcafe.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderInfoFragment extends Fragment {
    private DecimalFormat formatPrice = new DecimalFormat("###,###,###");
    public static final String TAG = OrderInfoFragment.class.getName();
    private Order order;
    private Home home;
    private View mView;
    private Button btnOrderInfoBack;
    private TextView tvOrderInfoNo,tvOrderInfoDate,tvOrderInfoCustName,tvOrderInfoCustAddress
            ,tvOrderInfoCustPhone,tvOrderInfoNum,tvOrderInfoTotal,tvOrderInfoStatus;
    private RecyclerView rcvOrderInfo;
    private OrderAdapter orderAdapter;
    private List<Order> listOrder;
    private List<Product> listProductCart;
    private OrderInfoFragment orderInfoFragment;
    public OrderInfoFragment() {
    }

    public OrderInfoFragment(Order order) {
        this.order = order;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_order_info, container, false);
        // Khởi tạo các item
        initItem();

        // Set nội dung cho các item
        setContentOrder();
        return mView;
    }
    // Khởi tạo các item
    private void initItem(){
        home = (Home) getActivity();
        listOrder = new ArrayList<>();
        listProductCart = new ArrayList<>();
        if(order!=null) Log.i("kiemobj","chacha");
//        orderInfoAdapter = new OrderInfoAdapter();
        tvOrderInfoNo = mView.findViewById(R.id.tv_order_info_no);
        tvOrderInfoDate = mView.findViewById(R.id.tv_order_info_date);
        tvOrderInfoCustName = mView.findViewById(R.id.tv_order_info_cust_name);
        tvOrderInfoCustAddress = mView.findViewById(R.id.tv_order_info_cust_address);
        tvOrderInfoCustPhone = mView.findViewById(R.id.tv_order_info_cust_phone);
        tvOrderInfoNum = mView.findViewById(R.id.tv_order_info_num);
        tvOrderInfoTotal = mView.findViewById(R.id.tv_order_info_total);
        tvOrderInfoStatus = mView.findViewById(R.id.tv_order_info_status);
        rcvOrderInfo = mView.findViewById(R.id.rcv_order_info);
        btnOrderInfoBack = mView.findViewById(R.id.btn_order_info_back);
        btnOrderInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.toHistoryFragment();
            }
        });
    }
    // Set nội dung cho các item
    private void setContentOrder(){
        tvOrderInfoNo.setText(order.getOrderNo().toUpperCase());
        tvOrderInfoDate.setText(order.getDateOrder());
        tvOrderInfoCustName.setText(order.getCustName());
        tvOrderInfoCustAddress.setText(order.getCustAddress());
        tvOrderInfoCustPhone.setText(order.getCustPhone());
        tvOrderInfoNum.setText(String.valueOf(order.getNumProduct()));
        tvOrderInfoTotal.setText(formatPrice.format(order.getTotalPrice()) + "VNĐ");
        tvOrderInfoStatus.setText(order.getStatus());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(home,RecyclerView.VERTICAL,false);
        rcvOrderInfo.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(order.getListProductCart());
        rcvOrderInfo.setAdapter(orderAdapter);
    }
}
