package com.example.dbwithlandr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
EditText a, b;
Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database db = new database(this);
        a = findViewById(R.id.inputEmail);
        b = findViewById(R.id.inputPassword);
        login = findViewById(R.id.btn);
        signup = findViewById(R.id.losignup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = a.getText().toString();
                String password = b.getText().toString();
                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "enter username and password", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean i = db.checkemailandpassword(email, password);
                    if( i == true){
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, Homepage.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "invalid incredential", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }

}
