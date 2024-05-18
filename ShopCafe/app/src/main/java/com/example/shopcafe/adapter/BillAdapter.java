package com.example.shopcafe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopcafe.Home;
import com.example.shopcafe.R;
import com.example.shopcafe.model.Bill;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder>{
    private List<Bill> billList;
    private Home home;

    public BillAdapter(List<Bill> billList, Home home) {
        this.billList = billList;
        this.home = home;
    }

    public BillAdapter(List<Bill> billList) {
        this.billList = billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
        notifyDataSetChanged();
    }

    public List<Bill> getBillList() {
        return billList;
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_history, parent, false);
        return new BillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        Bill bill = billList.get(position);
        if (bill == null) {
            return;
        } else {
            holder.tvBillId.setText(bill.getId());
            holder.tvBillName.setText(bill.getName());
            holder.tvBillPhone.setText(bill.getPhone());
            holder.tvBillDate.setText(bill.getDateOrder());
            holder.tvBillProducts.setText(bill.getBillProduct());
            holder.tvBillAddress.setText(bill.getAddress());
            holder.tvBillTotalPrices.setText(String.valueOf(bill.getTotalPrices()));
        }
    }

    @Override
    public int getItemCount() {
        return billList!=null ? billList.size() :0;
    }

    public class BillViewHolder extends RecyclerView.ViewHolder{
        TextView tvBillId, tvBillName, tvBillPhone, tvBillDate, tvBillProducts, tvBillAddress, tvBillTotalPrices;
        public BillViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBillId = itemView.findViewById(R.id.tv_search_hitory_product_orderNo);
            tvBillName = itemView.findViewById(R.id.tv_search_hitory_product_name);
            tvBillPhone = itemView.findViewById(R.id.tv_search_hitory_product_phone);
            tvBillDate = itemView.findViewById(R.id.tv_search_hitory_product_date);
            tvBillProducts = itemView.findViewById(R.id.tv_search_hitory_product_sp);
            tvBillAddress = itemView.findViewById(R.id.tv_search_hitory_product_adress);
            tvBillTotalPrices = itemView.findViewById(R.id.tv_search_hitory_product_totalPtice);
        }
    }
}
