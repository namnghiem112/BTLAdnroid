package com.example.shopcafe.fragment;

import static com.example.shopcafe.Home.HOME_USER_NAME;
import static com.example.shopcafe.login.LoginActivity.USER_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.shopcafe.Home;
import com.example.shopcafe.R;
import com.example.shopcafe.db.SQLiteHelper;
import com.example.shopcafe.login.LoginActivity;
import com.example.shopcafe.model.UserDetail;


public class UserFragment extends Fragment {
    private View mView;
    private Home home;
    TextView profileName, profileEmail, profileContact, profileBirthdate;
    TextView titleName;
    String nameFromDB,emailFromDB,birthdateFromDB,contactFromDB;
    Button logout;
    public UserFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_user, container, false);
        // Inflate the layout for this fragment
        initItem();
        return mView;
    }

    private void initItem() {
        profileName = mView.findViewById(R.id.profileName);
        profileEmail = mView.findViewById(R.id.profileEmail);
        profileContact = mView.findViewById(R.id.profileContact);
        profileBirthdate = mView.findViewById(R.id.profileBirthdate);
        titleName = mView.findViewById(R.id.titleName);
        logout = mView.findViewById(R.id.logout);
        showAllUserData();
//        Intent i = Intent.getIntentOld();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mView.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showAllUserData(){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
//        Intent i = getContext();
        UserDetail userDetails ;
        if(USER_NAME!=null) {
            userDetails = sqLiteHelper.getProfileByUserName(USER_NAME);
        }
        else
            userDetails = sqLiteHelper.getProfileByUserName("nam@gmail.com");
//        userDetails = sqLiteHelper.getProfileByUserName(USER_NAME);
        if(userDetails!=null){
            String nameUser = userDetails.getName();
            String contactUser = userDetails.getContact();
            String emailUser = userDetails.getEmail();
            String birthdateUser = userDetails.getBirthdate();
            titleName.setText(nameUser);
            profileName.setText(nameUser);
            profileEmail.setText(emailUser);
            profileContact.setText(contactUser);
            profileBirthdate.setText(birthdateUser);
        }
        else {
            Toast.makeText(mView.getContext(), "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
        }
    }
}
