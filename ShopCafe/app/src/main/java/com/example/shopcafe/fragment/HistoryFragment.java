package com.example.shopcafe.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcafe.Home;
import com.example.shopcafe.R;
import com.example.shopcafe.adapter.BillAdapter;
import com.example.shopcafe.adapter.ProductAdapter;
import com.example.shopcafe.db.SQLiteHelper;
import com.example.shopcafe.model.Bill;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HistoryFragment extends Fragment {
    private Home home;

    private View mView;
    private RecyclerView rcvBillAdapter;

    private BillAdapter billAdapter;

    public HistoryFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_history,container,false);
        initItem();

        setDataBillAdapter();
        return mView;
    }

    private void setDataBillAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(home, 1);
        rcvBillAdapter.setLayoutManager(gridLayoutManager);
        billAdapter = new BillAdapter(getListBill());
        rcvBillAdapter.setAdapter(billAdapter);
    }

    private List<Bill> getListBill() {
        SQLiteHelper db = new SQLiteHelper(getActivity());
        return db.getAllBill();
    }
    @Override
    public void onResume() {
        super.onResume();

    }
    // Khởi tạo các item
    private void initItem(){
        home = (Home) getActivity();
        rcvBillAdapter = mView.findViewById(R.id.rcv_hitory_search);
    }
}
