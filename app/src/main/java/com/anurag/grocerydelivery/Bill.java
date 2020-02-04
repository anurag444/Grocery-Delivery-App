package com.anurag.grocerydelivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Bill extends AppCompatActivity {

    private Button confirm,cancel;
    private TextView bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        setTitle("Check Out");

        confirm=findViewById(R.id.confirm);
        cancel=findViewById(R.id.cancel);
        bill=findViewById(R.id.bill);


        String Name=Details.name;
        String Bill= Details.price;
        bill.setText(Name+":  "+ Bill);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(Bill.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(Bill.this,"Your Order Is Confirmed",Toast.LENGTH_LONG).show();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Bill.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
