package com.anurag.grocerydelivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterAddress extends AppCompatActivity {

    private Button next;
    EditText name, address,state,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_address);
        setTitle("Address");

        next=findViewById(R.id.next);
        name=findViewById(R.id.addressName);
        address=findViewById(R.id.address);
        state= findViewById(R.id.addressState);
        country=findViewById(R.id.addressCountry);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().equals("")||
                        address.getText().toString().equals("")||
                        state.getText().toString().equals("")||
                        country.getText().toString().equals("")) {
                    Toast.makeText(EnterAddress.this, "Invalid/Empty Details", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(EnterAddress.this, Bill.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

                }
            }
        });


    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
