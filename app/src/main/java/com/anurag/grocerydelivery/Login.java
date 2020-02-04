package com.anurag.grocerydelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button login;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        login = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Logging In " + username.getText());
                progressDialog.show();
                if (username.getText().toString().equals("") | password.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    Intent intent = new Intent(Login.this, MainActivity.class);

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();


//                    progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
