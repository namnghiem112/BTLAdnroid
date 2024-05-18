package com.example.shopcafe.login;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopcafe.Home;
import com.example.shopcafe.R;
import com.example.shopcafe.db.SQLiteHelper;
import com.example.shopcafe.model.User;
import com.example.shopcafe.model.UserDetail;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText;
    private Button loginButton;
    public static String USER_NAME ="";
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        forgotPassword = findViewById(R.id.forgot_password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser();
            }
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    public void checkUser() {
        String userUsername = loginEmail.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();
        if (userUsername.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userUsername).matches()) {
            Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ email hợp lệ", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("login","1");
            SQLiteHelper db = new SQLiteHelper(getApplicationContext());
            UserDetail userDetails = db.getProfileByUserName(userUsername);
            if(userDetails.getEmail().equals(userUsername) && userDetails.getPassword().equals(userPassword)){
                Intent intent = new Intent(LoginActivity.this, Home.class);
                intent.putExtra("email", userUsername);
                USER_NAME = userUsername;
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công",
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else Toast.makeText(LoginActivity.this, "Đăng nhập thất bại",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
