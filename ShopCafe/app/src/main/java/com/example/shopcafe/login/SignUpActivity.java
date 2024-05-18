package com.example.shopcafe.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopcafe.R;
import com.example.shopcafe.db.SQLiteHelper;
import com.example.shopcafe.model.User;


public class SignUpActivity extends AppCompatActivity {
    private EditText signupEmail, signupPassword;
    private Button signupButton;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupEmail = findViewById(R.id.sname);
        signupPassword = findViewById(R.id.semail);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteHelper db = new SQLiteHelper(getApplicationContext());
                String user = signupEmail.getText().toString();
                String pass = signupPassword.getText().toString();

                User hc = new User(user, pass);
                double randomDouble = Math.random();
                randomDouble = randomDouble * 100 + 1;
                int randomInt = (int) randomDouble;
                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ email hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (pass.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu phải chứa ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                } else {
                    if (!db.checkUserSigup(user)){
                        Toast.makeText(getApplicationContext(),"Ten tai khoan da ton tai!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        db.addUser(new User(user, pass));
                        Intent i = new Intent(SignUpActivity.this, CreateProfile.class);
                        i.putExtra("email",user);
                        i.putExtra("password",pass);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
}
