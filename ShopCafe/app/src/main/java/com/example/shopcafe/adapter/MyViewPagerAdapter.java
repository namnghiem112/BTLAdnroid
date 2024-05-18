package com.example.shopcafe.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    private List<Fragment> listFragment = new ArrayList<>();

    public MyViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public void addFragment(Fragment fragment){
        listFragment.add(fragment);
    }
    public void replaceFragment(int position, Fragment newFragment) {
        if (position >= 0 && position < listFragment.size()) {
            listFragment.set(position, newFragment);
            notifyDataSetChanged(); // Cập nhật Adapter để hiển thị Fragment mới
        }
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getItemCount() {
        if(listFragment!=null){
            return listFragment.size();
        }
        return 0;
    }
}
