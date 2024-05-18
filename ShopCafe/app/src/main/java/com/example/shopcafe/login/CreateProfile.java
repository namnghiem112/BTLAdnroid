package com.example.shopcafe.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopcafe.R;
import com.example.shopcafe.db.SQLiteHelper;
import com.example.shopcafe.model.UserDetail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateProfile extends AppCompatActivity {
    ArrayAdapter ad;
    EditText Name, contact, birthdate;
    TextView Email, pass;

    Button CreateProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        Name = findViewById(R.id.sname);
        Email = findViewById(R.id.semail);
        birthdate = findViewById(R.id.birthdate);
        contact = findViewById(R.id.contact);
        pass = findViewById(R.id.pass);
        CreateProfile = findViewById(R.id.createProfile);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        Email.setText(email);
        pass.setText(password);
        CreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                String name = Name.getText().toString();
                String contactn = contact.getText().toString();
                String Birthdate = birthdate.getText().toString();
                String phoneRegex = "^0\\d{9}$";
                Pattern phonePattern = Pattern.compile(phoneRegex);
                Matcher phoneMatcher = phonePattern.matcher(contactn);
                String pattern = "\\d{2}-\\d{2}-\\d{4}"; // Biểu thức chính quy để kiểm tra định dạng xx-xx-xxxx
                if (name.isEmpty() || contactn.isEmpty() || Birthdate.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!phoneMatcher.matches()) {
                    Toast.makeText(getApplicationContext(), "Số điện thoại nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (!Birthdate.matches(pattern)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập ngày sinh đúng định dạng (xx-xx-xxxx)", Toast.LENGTH_SHORT).show();
                } else {
                    UserDetail helperClass = new UserDetail(password, name, email, contactn, Birthdate);
                    db.addProfile(helperClass);
                    Toast.makeText(getApplicationContext(), "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(com.example.shopcafe.login.CreateProfile.this, LoginActivity.class);
                    startActivity(in);
                }
            }
        });

    }
}
